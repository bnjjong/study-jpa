/*
 * Created By dogfootmaster@gmail.com on 2021
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2021/09/30
 */

package io.jjong.study.bookmanager.repository;

import io.jjong.study.bookmanager.domain.User;
import java.util.List;
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
  List<User> findByName(String name);

}
