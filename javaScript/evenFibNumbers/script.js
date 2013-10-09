function fib (max) {

	var firstFib = 1;
	var secondFib = 2;

	var fibArray = [];
	fibArray[0] = firstFib;
	fibArray[1] =secondFib;

	for (var i = 2; i < 10000000; i++) {
		if ((fibArray[i - 1] + fibArray[i -2]) > max) {
			break;
		} else {
			fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
		};
		// console.log(fibArray[i]);
	}
	return fibArray;
}

function printArray (fibArray) {
	for (var i = 0; i < fibArray.length; i++){
		console.log(fibArray[i]);
	}
}

printArray(fib(90));