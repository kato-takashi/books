(function(){
	var results;
	this.assert = function assert(value, desc){
		var li = document.createElement("li");
		li.className = value ? "pass":"fail";
		li.appendChild(document.createTextNode(desc));
		results.appendChild(li);
		if(!value){
			li.parentNode.parentNode.className = "fail";
		}
		return li;
	};
	this.test = function test(name, fn){
		results = document.getElementById("results");
		results = assert(true, name).appendChild(document.createElement("ul"));
		fn();
	};
})();

window.onload = function(){
	test("テストA.", function(){
		assert(true, "第1のアサート完了");
		assert(true, "第2のアサート完了");
		assert(true, "第3のアサート完了");
	});
	test("テストB.", function(){
		assert(true, "第1のアサート完了");
		assert(false, "第2のアサート失敗");
		assert(true, "第3のアサート完了");
	});
	test("テストC.", function(){
		assert(null, "失敗");
		assert(5, "合格");
	});
};