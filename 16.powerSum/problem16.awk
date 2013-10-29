#~ /bin/awk -f
BEGIN {
	sum = 1;
	for (i = 1; i <=1000; i++) {
		sum *=2;
	}
	printf("%f\n", sum);
}
