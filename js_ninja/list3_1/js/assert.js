function isNimble(){
	return true; //名前付きの関数を宣言　暗黙のうちにwindowのプロパティとして追加
};

assert(typeof window.isNimble === "function", "isNimble()は定義されている");
assert(isNimble.name === "isNimble", "isNimble()には名前がある");

var canFly = function(){
	return true;
};

assert(typeof window.canFly === "function", "canFly()は定義されている");
assert(canFly.name === "", "canFly()は名前がない");

window.isDeadly = function(){
	return true;
};

assert(typeof window.isDeadly === "function", "isDeadly()は定義されている");

function outer(){
	assert(typeof inner === "function", "inner()は宣言の前にスコープに入っている");
	function inner(){};
	assert(typeof innner === "function", "inner()は宣言の後にスコープに入っている");
	assert(window.inner === undefined, "inner()はグローバルスコープに入っていない");
};

outer();
assert(indow.innner === undefined, "inner()はまだグローバルスコープに入っていない");
window.wieldsSword = function swingsSword(){
	return true;
};

assert(window.wieldsSword.name === 'swingsSword', "wieldSwordの本当の名前はswingsSword");
