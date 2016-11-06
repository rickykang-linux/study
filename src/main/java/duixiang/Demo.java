package duixiang;

public class Demo {

	private int num;
	private String name = "aaa";
	
	static{
		System.out.println("static");
	}
	
	{
		System.out.println("num: "+num);
		System.out.println("name: "+name);
	}
	
	public Demo() {
		this.num = 10;
		this.name = "liben";
		System.out.println("Demo() " + num +", " + name);
	}
	
	public static void main(String[] args) {
//		new Demo();
		Pojo pojo = new Pojo();
		pojo.pojostring = "aaa";
		test(pojo);
		System.out.println(pojo.pojostring);

	}

	public static void test(Pojo aa){
		aa.pojostring = "00";
	}
}

class Pojo{
	public String pojostring;
}
