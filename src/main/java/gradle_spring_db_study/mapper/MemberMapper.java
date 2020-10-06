package gradle_spring_db_study.mapper;

import java.util.List;

import gradle_spring_db_study.spring.Member;

public interface MemberMapper {

	List<Member> selectAll();

	int insert(Member member);

	/*
	public Member selectByEmail(String email);
	
	public void update(Member member);
	
	public int count();
	
	public void delete(Member member);
	*/
}
