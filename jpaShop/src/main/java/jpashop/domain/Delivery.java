package jpashop.domain;

import javax.persistence.*;

@Entity
public class Delivery extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String city;
    private String street;
    private String zipCode;
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;
}
/*
주인은 mappedBy를 사용하지 않고 joinColumn
주인이 아니면 mappedBy 속성으로 주인을 지정한다.
외래키가 있는 곳이 주인
joinColumn이 있는 곳이 주인
 */