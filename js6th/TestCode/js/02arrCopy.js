//javascript 6版　
//p47 オブジェクトの値　参照型
/*
var a = []; //空の配列を参照する変数a
var b = a ; //これでbも同じ配列を参照
b[0] = 1; //変数bを使って配列を参照し更新する
a[0]　//=> 1:変更された内容は変数aからも確認できる
a==b;　//=> true: aとbはおなじオブジェクトを参照するので、等しいと判定される
*/

//P48 配列の要素をコピー　
/*
var a = ['a', 'b', 'c'];
var b = []; //コピー先になる配列
for(var i = 0; i<a.length; i++){
	b[i] = a[i]; //配列の要素をbにコピー
}
*/

function equalArrays(a, b){
	if(a.length != b.length) return false; //大きさの異なる配列は等しくない
	for(var i=0; i<a.length; i++){//すべての要素を巡回する
		if(a[i] !== b[i]){
			return false; //一つでも違っていれば等しくない
		}
	}
}
