// 変数型を拡張する
Function.prototype.method = function(name, func){
	this.prototype[name] = func;
	return this;
}

// 整数型変数が存在しないため、数値の整数部分だけを取り出す

Number.method('integer', function(){
	return Math[this<0? 'ceil':'floor'](this);
});
document.writeln((-10/3).integer());

// 空白を取り除くメソッド
String.method('trim', function(){
	return this.replace(/^\s+|\s+$/g, '');
});

document.writeln('"' + " neat ".trim() +'"');