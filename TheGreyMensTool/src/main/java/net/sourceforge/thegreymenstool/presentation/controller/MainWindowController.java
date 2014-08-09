package net.sourceforge.thegreymenstool.presentation.controller;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.Action;

import net.sourceforge.thegreymenstool.domain.ProjectActivityExecution;
import net.sourceforge.thegreymenstool.presentation.model.MainWindowPresentationModel;
import net.sourceforge.thegreymenstool.presentation.model.TimerStatus;
import net.sourceforge.thegreymenstool.presentation.view.MainWindowView;
import net.sourceforge.thegreymenstool.service.DomainService;

import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
public class MainWindowController {
	@Autowired
	private MainWindowPresentationModel model;
	@Autowired
	private MainWindowView view;
	@Autowired
	private DomainService domainService;

	private Timer timer = new Timer();

	private Action startTimerAction = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (model.getTimerStatus()) {
			case RUNNING:
				MainWindowController.this.stopTimer();
				view.getStartTimerButton().setText("Start");
				break;
			case STOPPED:
				MainWindowController.this.startTimer();
				view.getStartTimerButton().setText("Stop");
				break;
			default:
				break;
			}
		}

	};

	private TimerTask timerTask = new TimerTask() {

		@Override
		public void run() {
			switch (model.getTimerStatus()) {
			case RUNNING:
				ProjectActivityExecution ae = model
						.getCurrentProjectActivityExecution();
				ae.setEndTime(new Date());
				domainService.update(ae);
				break;
			default:
				break;
			}
		}
	};

	public void startTimer() {
		assert model.getTimerStatus() == TimerStatus.STOPPED;
		ProjectActivityExecution pae = domainService.createProjectActivityExecution();
		pae.setProjectActivity(model.getCurrentProjectActivity());
		domainService.update(pae);
		model.setCurrentProjectActivityExecution(pae);
		model.setTimerStatus(TimerStatus.RUNNING);
		timer.schedule(timerTask, 0, 1000);
	}

	protected void stopTimer() {
		assert model.getTimerStatus() == TimerStatus.RUNNING;
		model.setTimerStatus(TimerStatus.STOPPED);
		timer.cancel();
		timer.purge();
	}

	public Action getStartTimerAction() {
		return startTimerAction;
	}
}
