//
//  ModalViewController.m
//  myFirstApp01
//
//  Created by Kato Takashi on 2013/10/05.
//  Copyright (c) 2013年 Kato Takashi. All rights reserved.
//

#import "ModalViewController.h"

@interface ModalViewController ()

@end

@implementation ModalViewController
-(IBAction)respondToButtonClick:(id)sender{
//    画面を閉じる処理
    [self dismissViewControllerAnimated:YES completion:nil	];
}
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
    //    ボタンオブジェクトを生成する
    UIButton *button = [UIButton buttonWithType:UIButtonTypeRoundedRect];
    //    ボタンの大きさを表示させる場所を指定する
    button.frame = CGRectMake(0, 0, 100, 44);
    //    ボタンに表示させる文字列の設定
    [button setTitle:@"Return" forState:UIControlStateNormal];
    //    ボタンが押された時に呼び出されるメソッドの設定
    [button addTarget:self action:@selector(respondToButtonClick:) forControlEvents:UIControlEventTouchUpInside];
    //    ボタンにビュー階層を追加
    [self.view addSubview:button];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
