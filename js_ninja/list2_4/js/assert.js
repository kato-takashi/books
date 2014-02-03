function assert(value, desc) {				//#1
	var li = document.createElement("li");			//
	li.className = value ? "pass" : "fail";			//
	li.appendChild(document.createTextNode(desc));		//
	document.getElementById("results").appendChild(li);	//
      }								//

	window.onload = function() {
		assert(true, "テストスイートを実行中。");		//#2
		assert(false, "失敗!");					//
      };