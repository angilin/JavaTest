package base;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class FastJsonUtil {
	public static void main(String[] args) {
		String s = "{\"id\":null,\"name\":\"aaa\",\"amount\":121.01}";

		Cat cat = JSONObject.parseObject(s, Cat.class);
		System.out.println(cat.getId());
		System.out.println(cat.getAmount());

		// abc为Cat中不存在的字段，转成Cat对象会出现错误
		s = "{\"id\":null,\"name\":\"aaa\",\"amount\":121.01,\"abc\":{\"abcd\":111}}";
		Map map = JSONObject.parseObject(s, Map.class);
		System.out.println(map.get("id"));
		System.out.println(map.get("name"));
		System.out.println(map.get("amount"));
		System.out.println(((Map) map.get("abc")).get("abcd"));

		A a = new A();
		a.setId(1L);
		List<Cat> catList = new ArrayList<Cat>();
		Cat cat1 = new Cat();
		cat1.setAmount(new BigDecimal("11.1"));
		Cat cat2 = new Cat();
		cat2.setAmount(new BigDecimal("12.1"));
		catList.add(cat1);
		catList.add(cat2);
		a.setCatList(catList);

		System.out.println(JSONObject.toJSONString(a));

		A a2 = JSONObject.parseObject(JSONObject.toJSONString(a), A.class);
		System.out.println(a2.getCatList().get(1).getAmount());

		A a3 = new A();
		BeanMapper.copy(a2, a3);
		System.out.println(a3.getCatList().get(1).getAmount());

	}

	public static class Cat {
		private Long id;
		private String name;
		private BigDecimal amount;

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static class A {
		Long id;
		List<Cat> catList;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public List<Cat> getCatList() {
			return catList;
		}

		public void setCatList(List<Cat> catList) {
			this.catList = catList;
		}
	}

}
