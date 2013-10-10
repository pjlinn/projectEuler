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
	}
	return fibArray;
}

function isEven (fibArray) {
	var evenFibArray = [];
	var count = 0;
	for (var i = 0; i < fibArray.length; i++) {
		if (fibArray[i] % 2 == 0) {
			evenFibArray[count] = fibArray[i];
			count += 1;
		};
	}
	return evenFibArray;
}

function printArray (fibArray) {
	for (var i = 0; i < fibArray.length; i++){
		console.log(fibArray[i]);
	};
}

function printSum (evenFibArray) {
	var sum = 0;
	for (var i = evenFibArray.length - 1; i >= 0; i--) {
		sum += evenFibArray[i];
	};
	return sum;
}

// console.log(printSum(isEven(fib(4000000))));
// alert(printSum(isEven(fib(4000000))));