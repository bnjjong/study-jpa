/*
 * Created By dogfootmaster@gmail.com on 2021
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2021/09/30
 */

package io.jjong.study.bookmanager.repository;

import io.jjong.study.bookmanager.domain.User;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * create on 2021/09/30. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han
 * @version 1.0
 * @see
 * @since 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

  // return type에 대해서는 여러 타입들을 지원한다.
  // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-return-types

  // 이름도 아래 링크와 같이 지원하고 이를 IDE 에서 자동 완성 support 기능으로 제공 한다.
  // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.subject
  List<User> findByName(String name);

  User findByEmail(String email);
  User getByEmail(String email);
  User readByEmail(String email);
  User queryByEmail(String email);
  User searchByEmail(String email);
  User streamByEmail(String email);
  User findUserByEmail(String email);

  List<User> findFirst1ByName(String name);
  List<User> findTop1ByName(String name);

  List<User> findLast1ByName(String name);

  List<User> findUserByNameAndEmail(String name, String email);
  List<User> findUserByNameOrEmail(String name, String email);

  List<User> findByCreatedAtAfter(LocalDateTime localDateTime);
  List<User> findByIdAfter(long id);

  List<User> findByCreatedAtGreaterThan(LocalDateTime localDateTime);

  // 많이 사용하지 않음. 내부적으로 exists 로 처리 함.
//  List<User> findByAddressIsNotEmpty();

  List<User> findByNameIn(List<String> names);

  List<User> findTopByNameOrderByIdDesc(String name);

  List<User> findTopByNameOrderByIdDescEmailAsc(String name);

  List<User> findFirstByName(String name, Sort sort);

}
