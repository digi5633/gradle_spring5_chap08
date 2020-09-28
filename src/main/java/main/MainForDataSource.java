package main;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Member;
import spring.MemberDao;

public class MainForDataSource {

	private static MemberDao memberDao;

	public static void main(String[] args) throws IOException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);) {
			DataSource ds = ctx.getBean(DataSource.class);
			System.out.println(ds);

			memberDao = ctx.getBean(MemberDao.class);

			selectByEmail();
			selectAll();
			selectCount();
		}
		
	}

	private static void selectCount() {
		System.out.println("selectCount()");
		int count = memberDao.count();
		System.out.println("count > " + count);
	}

	private static void selectAll() {
		System.out.println("selectAll()");
		List<Member> list = memberDao.selectAll();
		list.stream().forEach(System.out::println);
	}

	private static void selectByEmail() {
		System.out.println("selectByEmail()");
		Member member = memberDao.selectByEmail("test@test.co.kr");
		System.out.printf("%d : %s : %s%n", member.getId(), member.getEmail(), member.getName());
	}
	
}
