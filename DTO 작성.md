## Example

- of :  dto -> dto
	- EntityDto를 생성하기 위한 메서드, 오버로딩을 사용할 목적으로 작성
- from : entity -> dto
	- entity를 Dto로 변경하기 위한 목적의 메서드
- toEntity : dto -> entity
	- dto를 entity로 변경하기 위한 목적의 메서드

#### Entity
```java
@Entity
public class Entity{
	private Long id,
	privte String content

	public Entity(Long id, String content){
		this.id = id;
		this.content = content;
	}

	public static Entity of(Long id, String content){
		return new Entity(id,content);
	}
}
```

#### Dto
```java
public record Dto(
	Long id,
	String content
){
	public Dto(Long id,String content){
		this.id = id;
		this.content = content;
	}
	
	public static Dto of(Long id){
		return Dto.of(id,null);
	}

	public static Dto of(Long id, String content){
		return new Dto(id,content);
	}

	public static Dto from(Entity entity){
		return Dto.of(entity.getId(), entity.getContent());
	}

	public Entity toEntity(){
		return Entity.of(id,content);
	}
}
```

```java
public static void main(String[] args){
	//둘다 동일한 방식이며, 오버로딩을 통해 생산성을 늘리고자 of 메서드 사용
	Dto dto1 = new Dto(id,content);
	
	Dto dto2 = Dto.of(id,content);

	//entity 형식을 dto 형식으로 쉽게 변경하기 위한 메서드이며, of와 마찬가지로 필드 수를 변경하여 같은 이름으로 오버로딩이 가능
	Dto dto3 = Dto.from(entity);

	//dto 형식을 entity 형식으로 쉽게 변경하기 위한 메서드이며, static 형식이 아닌 이유는 새로 값을 넣어줄 필요 없이 기존에 생성된 dto를 그대로 변형하기 위함
	Entity entity = dto3.toEntity();
}
```

## ObjectMapper와 비교

```java
	private ObjectMapper mapper = new ObjectMapper();

	public void test(){
		Entity entity = new Entity(id,content);
		
		// ObjectMapper를 사용하여 entity라는 domain을 Dto로 변경하는 로직
		Dto dto1 = mapper.convertValue(entity,Test.class);

		// 기존의 방식인 from 메서드 이용 entity -> dto
		Dto dto2 = Dto.from(entity);
	}
```

ObjectMapper를 이용하여 매핑을 하게 되면 entity와 dto가 완전 동일한 구성이 되어야지만 연동이 가능한 형태이다. 

from, of 메서드를 이용하면 ObjectMapper의 기능 및 유동적으로 객체를 생성하고 변경할 수 있다.

또한 Service 단계에서 ObjectMapper를 각각 선언하지 않아도 되며 단순 중복 코드 작성을 막을 수 있다.
