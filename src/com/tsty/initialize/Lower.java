package com.tsty.initialize;

/** */

/**
 * 1.main() 函数调用了 Lower 构造器。
 * 
 * 2.Lower 的一个实例被准备好了。意味着所有的字段都被创建并且填充了默认值，例如，引用类型的默认值为空，布尔类型的默认值为 false
 * 。在这个时候，任何的对字段的内联赋值都没有发生。
 * 
 * 3.父类构造器被调用了。这是被语言的特性所强制执行的。所以在其他任何事发生之前，Upper 的构造器被调用了。
 * 
 * 4.Upper 这个构造器运行并且指定了一个引用，指向 Initializer.initialize() 方法新创建的的实例。
 * 
 * 5.Initializer 类为两个字段（ upperString 和 lowerString ）附上新字符串。通过使用有点肮脏的 instanceof
 * 实例检查做到为那两个字段赋值 – 这不是一个特别好的设计模式，但是也有可行的，不用管那么多。一旦发生了，upperString 和 lowerString
 * 的引用都不再为空。
 * 
 * 6.Initializer.initialize() 的调用完成，Upper 构造器也同样完成。
 * 
 * 7.现在变得有趣了：Lower 实例的构造在继续。假设在 lowerString 字段的声明中没有明确地 =null 赋值，Lower
 * 构造器恢复执行并且打印出两个连接到字段的字符串。
 * 
 * 然而，如果有一个明确地赋值 null
 * 的操作，执行流程会略有不同：当父类构造器完成后，在其余的构造器运行前，任何变量初始化都会执行（参见java语言规范12.5节）。在这种情况下，之前赋值给
 * lowerString 的字符串引用会再一次被赋予 null 。然后继续执行其余的函数构造，现在打印 lowerString 的值为: null 。
 * 
 */

public class Lower extends Upper {


	/**
	 * 当String lowerString = null;打印结果是 Upper: upperInited Lower: null
	 */
//	 String lowerString = null;
	// 打印结果是：

	/**
	 * 当String lowerString;打印结果是 Upper: upperInited Lower: lowerInited
	 */
	String lowerString;

	public Lower() {
		super();
		System.out.println("Upper:  " + upperString);
		System.out.println("Lower:  " + lowerString);
	}

	public static void main(final String[] args) {
		new Lower();

	}
}
