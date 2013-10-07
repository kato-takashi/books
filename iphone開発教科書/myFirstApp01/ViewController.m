//
//  ViewController.m
//  myFirstApp01
//
//  Created by Kato Takashi on 2013/10/05.
//  Copyright (c) 2013年 Kato Takashi. All rights reserved.
//

#import "ViewController.h"
#import "ModalViewController.h"

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
    
//    ボタンオブジェクトを生成する
    UIButton *button = [UIButton buttonWithType:UIButtonTypeRoundedRect];
//    ボタンの大きさを表示させる場所を指定する
    button.frame = CGRectMake(0, 0, 100, 44);
//    ボタンに表示させる文字列の設定
    [button setTitle:@"button" forState:UIControlStateNormal];
//    ボタンが押された時に呼び出されるメソッドの設定
    [button addTarget:self action:@selector(respondToButtonClick:) forControlEvents:UIControlEventTouchUpInside];
//    ボタンにビュー階層を追加
    [self.view addSubview:button];
    
}
-(void)respondToButtonClick:(id)sender {
 //ボタンが押された時の処理
    //ビューコントローラーオブジェクトを生成する
    ModalViewController *controller = [[ModalViewController  alloc] init];
//    背景色を白に設定する
    controller.view.backgroundColor = [UIColor whiteColor];
//    ビューコントローラをモーダルで遷移させる
    [self presentViewController:controller animated:YES completion:nil];
    
    
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
