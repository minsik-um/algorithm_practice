https://stackify.com/java-performance-tuning/

### StringBuilder
loop를 통해 여러개의 Strings 값을 concatenate 할 때는 `StringBuilder`를 사용하자.

```java
StringBuilder sb = new StringBuilder(“This is a test”);
for (int i=0; i<10; i++) {
    sb.append(i);
    sb.append(” “);
}
log.info(sb.toString());
```

한 개 값을 concatenate 할 때는 `+` 연산자를 사용하자.

```java
Query q = em.createQuery("SELECT a.id, a.firstName, a.lastName "
+ "FROM Author a "
+ "WHERE a.id = :id");
```

### Primitive value
과부하를 막고 성능 향상을 하려면 가능한 wrapper class 대신에 primitive type을 쓰자. 예를 들어 Integer 대신에 int, Double 대신에 double을 쓰는 것이다. 

JVM은 값들을 heap에 저장하는 대신 stack에 저장하여 메모리 소비를 줄이고 효과적으로 다룰 수 있다. 

![](https://stackify.com/wp-content/uploads/2017/10/https-lh6-googleusercontent-com-qaadmrc1c1mr83-e.png)

### BigInteger와 BigDecimal을 쓰지 않도록 하자.
정확도는 올라가지만 메모리 차지가 크다.

### String.replace 대신에 Apach Commons의 StringUtils.replace를 사용하자.

```java
// replace this
test.replace(“test”, “simple test”);

// with this
StringUtils.replace(test, “test”, “simple test”);
```

### DB 연결 같이 비용이 크거나 자주 사용하는 자원은 Cache를 사용하자.
caching은 반복적인 실행으로 큰 비용이 들거나 빈번한 코드 사용을 방지하는 유명한 방법이다.

[캐시](https://aws.amazon.com/ko/caching/)는 (기본 스트리지 대신에) 고속 데이터 스토리지 계층에 일시적인 특징이 있는 데이터를 저장하는 방법이다. 자주 사용하는 자원을 캐시에 저장해놓고 사용한다(캐싱)

캐시의 데이터는 보통 RAM과 같이 빠른 하드웨어에 저장된다. 