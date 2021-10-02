/*
 * Created By dogfootmaster@gmail.com on 2021
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2021/09/30
 */

package io.jjong.study.bookmanager.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


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
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "user_legacy")
// name column 으로 index 설정 하고 email 로 unique 설정을 함.
// DB 내에서 설정하는 것이 더 좋을 것 같다.
@Table(indexes = {@Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

  /**
   * SEQUENCE : Oracle, Postgre, h2에서 사용 시퀀스를 사용하는 전략.
   * IDENTITY : 데이터 베이스에 위임. Auto incresement
   * AUTO : 자동 지정, 기본 값. 데이터 베이스에 의존하지 않고 기본키를 할당 함. 오라클의 경우 자동으로 SEQUENCE 자동으로 선택 함.
   * TABLE : 키 생성용 테이블 사용, 모든 DB에서 사용 가능 Id 를 별도로 관리하는 테이블로
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NonNull
  private String name;
  @NonNull
  private String email;

  @Column(updatable = false)
  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  // 영속성 데이터에서는 제외 함.
  @Transient
  private String testData;

  // String 으로 해줘야 숫자 형태 값으로 안들어 감!! 중요 함.
  @Enumerated(value = EnumType.STRING)
  private Gender gender;

//  @OneToMany(fetch = FetchType.EAGER)
//  private List<Address> address;

}
