//
//  ViewController.m
//  myFirstApp01
//
//  Created by Kato Takashi on 2013/10/05.
//  Copyright (c) 2013年 Kato Takashi. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
    UIView *view = [[UIView alloc] initWithFrame:CGRectMake(100, 400, 100, 100)];
    //    UIViewオブジェクトの背景色のデフォルト値は透明なため背景色を別の色に設定
    view.backgroundColor = [UIColor redColor];
    //    viewプロパティにサブビューを追加
    [self.view addSubview:view];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
