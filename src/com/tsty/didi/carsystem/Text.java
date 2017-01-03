package com.tsty.didi.carsystem;

/**
 * 代码出自网址：http://www.imooc.com/article/15629?block_id=tuijian_wz
 */

import java.util.Scanner;

public class Text extends Capacity {
	@Override
	public int passengerCapacity(int e[], int d) {
		int humannumber = 0;
		int money = 0;
		int eeeee = 0, ee = 0, eee = 0, eeee = 0;
		for (int j = 0; j < d; j++) {
			if (e[j] == 1) {
				eeeee = eeeee + 1;
				System.out.print("奥迪A4: " + eeeee + "辆 ");
				humannumber = humannumber + 4;
				money = money + 500;
			} else if (e[j] == 2) {
				ee = ee + 1;
				System.out.print("马自达6: " + ee + "辆 ");
				humannumber = humannumber + 4;
				money = money + 400;
			} else if (e[j] == 3) {
				eee = eee + 1;
				System.out.print("皮卡雪6: " + eee + "辆 ");
				humannumber = humannumber + 4;
				money = money + 450;
			} else if (e[j] == 4) {
				eeee = eeee + 1;
				System.out.print("金龙: " + eeee + "辆 ");
				humannumber = humannumber + 20;
				money = money + 800;
			}
		}
		System.out.print("总载客量为: ");
		System.out.println(humannumber);
		return money;
	}

	@Override
	public int carryingCapacity(int e[], int d) {
		int goodsquantity = 0;
		int money = 0;
		int o = 0, oo = 0, ooo = 0;
		for (int j = 0; j < d; j++) {
			if (e[j] == 3) {
				o = o + 1;
				System.out.print("皮卡雪6: " + o + "辆 ");
				goodsquantity = goodsquantity + 2;
			} else if (e[j] == 5) {
				oo = oo + 1;
				System.out.print("松花江: " + oo + "辆 ");
				goodsquantity = goodsquantity + 4;
				money = money + 400;
			} else if (e[j] == 6) {
				ooo = ooo + 1;
				System.out.print("依维柯: " + ooo + "辆 ");
				goodsquantity = goodsquantity + 200;
				money = money + 450;
			}
		}
		System.out.print("总载货量为: ");
		System.out.println(goodsquantity);
		return money;
	}

	public static void main(String[] args) {
		System.out.println("您是否需要呼叫张宸打车系统：1需要，0不需要");
		Scanner a = new Scanner(System.in);
		int b = a.nextInt();
		if (b == 1) {
			System.out.println("序号 汽车名称 租金 容量");
			System.out.println(" 1 奥迪A4 500元/天 载人:4人");
			System.out.println(" 2 马自达6 400元/天 载人:4人");
			System.out.println(" 3 皮卡雪6 450元/天 载人:4载货:2吨");
			System.out.println(" 4 金龙 800元/天 载人:20人");
			System.out.println(" 5 松花江 400元/天 载货:4吨");
			System.out.println(" 6 依维柯 1000元/天 载货:200吨");
			System.out.println("请输入您要选车辆数:");
			Scanner c = new Scanner(System.in);
			int d = c.nextInt();
			int[] e = new int[d];
			int k = 0;
			for (int i = 0; i < d; i++) {
				k = i + 1;
				System.out.println("请输入您要选择的第" + k + "辆车的序号");
				Scanner f = new Scanner(System.in);
				e[i] = f.nextInt();
			}
			int z = 0;
			Text text = new Text();
			z = text.passengerCapacity(e, d) + text.carryingCapacity(e, d);
			System.out.println("总价钱为: " + z);
		}
	}
}
