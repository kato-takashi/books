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
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
