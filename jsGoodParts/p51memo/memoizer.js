var memoizer = function (memo, fundamental){
	var shell = function(n){
		var result = memo[n];
		if(typeof result !== "number"){ //memo配列[0,1]以外の検出
			result = fundamental(shell, n);
			memo[n] = result;
		}
		return result;
	};
	return shell;
};

// 数列の例　以下メモとは無関係
// 　
// フィボナッチ
var fibonacci = memoizer([0, 1], function(shell, n){
	return shell(n-1) + shell(n-2); 
});


// 階乗計算
var factorial = memoizer([1, 1], function(shell, n){
	return n * shell(n-1);
});