package main;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
			insertMember();
			updateMember();
			deleteMember();
		}

	}

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");

	private static void insertMember() {
		System.out.println("-----insertMember");
		String prefix = formatter.format(LocalDateTime.now());
		Member member = new Member(prefix + "@test.co.kr", prefix, prefix, LocalDateTime.now());
		memberDao.insert(member);
		System.out.println(member.getId() + " 데이터추가");
	}

	private static void updateMember() {
		System.out.println("-----updateMember");
		Member member = memberDao.selectByEmail("test@test.co.kr");
		String oldPw = member.getPassword();
		String newPw = Double.toHexString(Math.random());
		member.changePassword(oldPw, newPw);
		memberDao.update(member);
		System.out.println("암호변경: " + oldPw + " > " + newPw);
		System.out.println(member.getId() + " 데이터삭제");
	}

	private static void deleteMember() {
		System.out.println("-----deleteMember");
		Member member = memberDao.selectByEmail("test@test.co.kr");
		memberDao.delete(member);
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
