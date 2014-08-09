import net.sourceforge.thegreymenstool.service.DomainService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext(
				"/config.xml");
		DomainService s = c.getBean("domainService",
				DomainService.class);
		s.createProject("Hallo");
		System.out.println(s.findAllProjects());
	}

}