package io.jjong.study.bookmanager.repository;/*
 * Created By dogfootmaster@gmail.com on 2021
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2021/09/30
 */

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import io.jjong.study.bookmanager.domain.User;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

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
@SpringBootTest // 스프링 컨텍스트를 로딩
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void crud() {
//    userRepository.save(new User());
//    userRepository.findAll().forEach(System.out::println);

    List<User> users = userRepository.findAll(Sort.by(Direction.ASC,"name"));
    users.forEach(System.out::println);

    users = userRepository.findAllById(Lists.newArrayList(1L,2L,3L));
    users.forEach(System.out::println);

    // save
    User user1 = new User("jongsang", "jongsang@mymail.com");
    User user2 = new User("george", "george@mymail.com");
    userRepository.saveAll(Lists.newArrayList(user1, user2));
    userRepository.findAll().forEach(System.out::println);

//    User user = userRepository.findById(1L).orElse(null);
    User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
    System.out.println(user);

    // commit
    userRepository.flush();
    userRepository.findAll().forEach(System.out::println);

    long count = userRepository.count();
    System.out.println("count : " + count);

    boolean exists = userRepository.existsById(1L);
    System.out.println("is exists : " + exists);

    // delete
    // select가 2번 일어남
//    userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));

    // select가 1번만 일어남
    userRepository.deleteById(1L);

    // 성능적인 이슈가 있을 수 있음. 조회 한뒤 삭제하므로
//    userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(2L, 3L)));

    // 조회를 하지 않고 삭제 하고 delete 할때 or 조건으로 삭제 한다.
    userRepository.deleteAllInBatch(userRepository.findAllById(Lists.newArrayList(2L,3L)));
    userRepository.findAll().forEach(System.out::println);

    // 전체 삭제 한방에 삭제.
    userRepository.deleteAllInBatch();
  }

  @Test
//  @Transactional
  void lazyLoding() {
    User user = userRepository.getOne(1L);
    System.out.println();
  }

  @Test
  @DisplayName("paging test")
  void paging() {
    // pageing zero based page start = 0
    Page<User> users = userRepository.findAll(PageRequest.of(0, 3));
    System.out.println("page : " + users);
    System.out.println("total elements : " + users.getTotalElements()); // 전체 엘리먼트 개수
    System.out.println("total pages : " + users.getTotalPages()); // 전체 페이지 수
    System.out.println("number of elements : " + users.getNumberOfElements()); // 현재 엘리먼트 개수
    System.out.println("sort : " + users.getSort()); // 정렬 정보
    System.out.println("size : " + users.getSize()); // fetch size

    users.getContent().forEach(System.out::println);
  }

  @Test
  void exampleMatcher() {
    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnorePaths("id")
        .withIgnorePaths("name")
        .withMatcher("email", GenericPropertyMatcher.of(StringMatcher.ENDING));

    Example<User> example = Example.of(new User("ma","fastcampus.com"), matcher);
    userRepository.findAll(example).forEach(System.out::println);

    // contains 검색
    User user = new User();
    user.setEmail("slow");

    ExampleMatcher matcher1 = ExampleMatcher.matching()
        .withIgnorePaths("id")
        .withMatcher("email", GenericPropertyMatcher.of(StringMatcher.CONTAINING));

    example = Example.of(user, matcher1);

    userRepository.findAll(example).forEach(System.out::println);
  }

  @Test
  void update() {
    userRepository.save(new User("david", "david@fastcampus.com"));

    User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
    user.setEmail("martin-update@fastcampus.com");

    // update 될때는 select 하여 데이터가 있을 경우 update 한다.
    userRepository.save(user);

  }

  @Test
  @DisplayName("name method test")
  public void selectName() throws Exception {
    // given
    String name = "martin";

    // when
    List<User> user = userRepository.findByName(name);

    // then
    System.out.println(user);
  }



}