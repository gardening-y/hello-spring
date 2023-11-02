package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {


//    JPQL -> select m from Member m where m.name = ?
//    위 처럼 인터페이스 이름만으로 끝남 NameAndId Or 등 다양하게 있음
    @Override
    Optional<Member> findByName(String name);

}
