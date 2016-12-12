package com.tsty.nashorn.javajs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * Nashorn JavaScript 引擎是Java SE 8的一部分，它与其它像Google V8 (它是Google Chrome
 * 和Node.js的引擎)的独立引擎相互竞争。 Nashorn 扩展了Java在JVM上运行动态JavaScript脚本的能力。
 * 
 * Nashorn javascript
 * 引擎要么在"java程序中以编程的方式使用","要么在命令行工具jjs使用"，jjs在目录$JAVA_HOME/bin中
 * 
 * 学习 Java 与 JavaScript 的相互调用。最后包括如何在日常的 Java 业务中整合动态脚本。
 *
 */

public class TestNashorn {
	public static void main(String[] args) {
		// testJSCode();
		// testJSFile();
		// testJavaInvoceJs();
		// testJavaInvoceJs2();

		testJSInvoceJava();

	}

	/** 在java中直接执行JavaScript代码 */
	private static void testJSCode() {
		try {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			engine.eval("print('Hello World!');");
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 在java中直接读取JS文件执行JavaScript代码 */
	private static void testJSFile() {
		try {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			engine.eval(new FileReader("script.js"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 为了调用函数，你首先得把脚本引擎转换为 Invocable。NashornScriptEngine 实现了 Invocable
	 * 接口且定义一个调用JavaScript函数的方法 invokeFunction ，传入函数名即可。
	 */
	private static void testJavaInvoceJs() {
		try {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			engine.eval(new FileReader("script.js"));

			System.err.println("&&&&&&&");

			Invocable invocable = (Invocable) engine;// 把脚本引擎转换为 Invocable

			Object result = invocable.invokeFunction("fun1", "Peter Parker");
			System.out.println(result);
			System.out.println(result.getClass());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 你可以传递任意 Java 对象而不会在 JavaScript 这边丢失类型信息。 因为脚本本身是在 JVM 虚拟机中执行的，我们可以完全利用
	 * nashorn 引擎的 Java API 和外部库的强大功能。
	 */
	private static void testJavaInvoceJs2() {
		try {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			engine.eval(new FileReader("script.js"));

			System.err.println("&&&&&&&");

			Invocable invocable = (Invocable) engine;// 把脚本引擎转换为 Invocable

			Object result1 = invocable.invokeFunction("fun2", new Date());
			// [object java.util.Date]

			Object result2 = invocable.invokeFunction("fun2", LocalDateTime.now());
			// [object java.time.LocalDateTime]

			Object result3 = invocable.invokeFunction("fun2", new Person());
			// [object com.winterbe.java8.Person]

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 在 JavaScript 端调用 Java 方法
	private static void testJSInvoceJava() {
		try {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			engine.eval(new FileReader("script.js"));
		} catch (FileNotFoundException | ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 定义一个JS调用Java的方法
	public static String fun1(String name) {
		System.out.format("Hi there from Java, %s", name);
		System.out.println();
		return "greetings from java";
	}

	// 定义一个JS调用Java的方法
	public static void fun2(Object object) {
		System.out.println(object.getClass());
	}

	/**
	 * 原始的javascript 类型被转换为适当的 java
	 * 包装器类。而不是本地javascript对象内部适配器类。请记住，这些类来自于jdk.nashorn.internal，
	 * 所以你不应该在客户端使用这些类:
	 * 
	 * ScriptObjectMirror ()
	 * 
	 * 当使用ScriptObjectMirror把本地JavaScript对象传入时，实际上是有一个java对象表示JavaScript 对象。
	 * ScriptObjectMirror 实现了接口与jdk.nashorn.api内部的映射。这个包下的类目的就是用于客户端代码使用。
	 * 
	 * 
	 */
	
	/** 以下示例更改参数类型Object为ScriptObjectMirror，因此我们能获取到传入JavaScript中对象的一些信息 */
	public static void fun3(ScriptObjectMirror mirror) {
	    System.out.println(mirror.getClassName() + ": " +
	        Arrays.toString(mirror.getOwnKeys(true)));
	}
	
	//javascript 函数Person的getFullName函数能被 ScriptObjectMirror 的callMember()调用。
	public static void fun4(ScriptObjectMirror person) {
	    System.out.println("Full Name is: " + person.callMember("getFullName"));
	}


}
