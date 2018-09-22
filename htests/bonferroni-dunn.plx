#!/usr/bin/perl -w

use strict;
use warnings;

use FileHandle;
use Data::Dumper;
use Statistics::Distributions;

my $fh = new FileHandle;

my $file;

$file = shift;

if (!defined($file)){
   print "USAGE: bonferroni-dunn.pl <datafile>\n";
   exit(0);
}

$fh->open("< $file") or die "Could not open $file";

my $line;

$line = $fh->getline();

chomp($line);

$line =~ s/^\s+//g;

my (@algs, $alg); 

@algs = split(/\s+/, $line);

my ($ds, @res, %results);

while($line = $fh->getline() or not eof($fh)){
  chomp $line;
  ($ds, @res) = split(/\s+/, $line);
  foreach $alg (@algs){
    $results{$ds}{$alg}{res} = shift @res;
  }
  
  my $rank_pos = 1;
  my $rank_value = 200;
  my @draw;
  my $i;
  my $rank_draw;
  my $mean_rank;

  foreach $alg (sort {$results{$ds}{$b}{res} <=> $results{$ds}{$a}{res}} keys %{$results{$ds}}){
  if ($results{$ds}{$alg}{res} < $rank_value){
    if (scalar(@draw) > 1){
      $mean_rank = ($rank_pos + $rank_draw -1)/2;
      foreach $i (@draw){
        $results{$ds}{$i}{rank} = $mean_rank;
      }
      undef(@draw);
    }
    $rank_value = abs($results{$ds}{$alg}{res});
    $results{$ds}{$alg}{rank} = $rank_pos;
    @draw = ($alg);
    $rank_draw = $rank_pos;
  }elsif($results{$ds}{$alg}{res} == $rank_value){
    push (@draw, $alg);
  }else{
    die "**************************$rank_value, ";
  }
    $rank_pos++;
  }
  
  if (scalar(@draw) > 1){
    $mean_rank = ($rank_pos + $rank_draw -1)/2;
    foreach $i (@draw){
      $results{$ds}{$i}{rank} = $mean_rank;
    }
    undef(@draw);
  }

}

my %avg;

foreach $alg (@algs){
  foreach $ds (keys %results){
    $avg{$alg} += $results{$ds}{$alg}{rank};
  }
}

my ($N, $k);
$N = scalar keys %results;
$k = scalar @algs;
my ($sum_R, $X_sq);

foreach $alg (@algs){
  $avg{$alg}/=$N;
  $sum_R+=$avg{$alg}**2;
}


$X_sq = ((12*$N)/($k*($k+1)))*($sum_R - (($k*($k+1)**2))/4);


my $chi = Statistics::Distributions::chisqrdistr($k-1,.05); 

printf "%20s\t","";
foreach $alg (@algs){
  printf ("%s\t\t",$alg);
}
printf "\n\n";

foreach $ds (sort keys %results){
  printf "%20s\t",$ds;
  foreach $alg (@algs){
    printf ("%.3f(%.1f)\t",$results{$ds}{$alg}{res},$results{$ds}{$alg}{rank});
  }
  printf "\n";
}

printf "\n%20s\t","average rank";
foreach $alg (@algs){
  printf ("%.3f\t\t",$avg{$alg});
}
printf "\n\n";


printf "The Chi-Square statistics is %.2f.\n",$X_sq;
printf "The critical value of the Chi\-square statistics with %d degrees of freedom and at 95 percentile is %.2f\n", $k-1, $chi;
if ($X_sq >= $chi){
  print "According to the Freidman test using the Chi-Square statistics, the null-hypothesis that all algorithms behave similar should be rejected\n\n";
}else{
  print "According to the Freidman test using the Chi-Square statistics, the null-hypothesis that all algorithms behave similar should not be rejected\n\n";
}

$chi = Statistics::Distributions::chisqrdistr($k-1,.1);

printf "The critical value of the Chi\-square statistics with %d degrees of freedom and at 90 percentile is %.2f\n", $k-1, $chi;
if ($X_sq >= $chi){
  print "According to the Freidman test using the Chi-Square statistics, the null-hypothesis that all algorithms behave similar should be rejected\n\n";
}else{
  print "According to the Freidman test using the Chi-Square statistics, the null-hypothesis that all algorithms behave similar should not be rejected\n\n" }
    

my $F = (($N-1)*$X_sq)/($N*($k-1)-$X_sq);

my $ff = Statistics::Distributions::fdistr($k-1,($k-1)*($N-1),.05);

printf "The F-statistics is %.2f.\n",$F;

printf "The critical value of the F-statistics with %d and %d degrees of freedom and at 95 percentile is %.2f\n", $k-1, ($N-1)*($k-1), $ff;
if ($F >= $ff){
  print "According to the Freidman test using the F-statistics, the null-hypothesis that all algorithms behave similar should be rejected\n\n";
}else{
  print "According to the Freidman test using the F-statistics, the null-hypothesis that all algorithms behave similar should not be rejected\n\n";  
}

$ff = Statistics::Distributions::fdistr($k-1,($k-1)*($N-1),.1);

printf "The critical value of the F-statistics with %d and %d degrees of freedom and at 90 percentile is %.2f\n", $k-1, ($N-1)*($k-1), $ff;
if ($F >= $ff){
  print "According to the Freidman test using the F-statistics, the null-hypothesis that all algorithms behave similar should be rejected\n\n";
}else{
  print "According to the Freidman test using the F-statistics, the null-hypothesis that all algorithms behave similar should not be rejected\n\n";
}

my $control = shift or die "You should specify a dataset as control in order to run this test";

print "Running the Bonferroni-Dunn post-hoc test to verify wheter it is possible to detect differences with the control $control.\n\n";

my $qa = {
2 => 1.960,
3 => 2.241,
4 => 2.394,
5 => 2.498,
6 => 2.576,
7 => 2.638,
8 => 2.690,
9 => 2.724,
10 => 2.773,
11 => 2.807,
12 => 2.838, 
13 => 2.866,
14 => 2.891,
15 => 2.914,
16 => 2.936,
17 => 2.955,
18 => 2.974,
19 => 2.992,
20 => 3.008,
21 => 3.024, 
22 => 3.038,
23 => 3.052,
24 => 3.066,
25 => 3.078,
};



my $CD = $qa->{$k}*sqrt(($k*($k+1))/(6*$N));

printf "According to the Bonferroni-Dunn statistis, the critical value for comparing the mean-ranking of an algorithm to a control at 95 percentile is %.2f. Mean-rankings differences above this value are significative.\n\n",$CD;


foreach $alg (sort {$avg{$a} <=> $avg{$b}} keys %avg){
 if ($alg eq $control){
 
 }elsif($avg{$alg} - $avg{$control} >= $CD){
   print "$alg is worst than $control\n";
 }elsif($avg{$control} - $avg{$alg} >= $CD){
   print "$alg is better than $control\n";
 }else{
   print "It is not possible to detect any significant difference between $alg and $control\n";
 }
}


$qa = {
2 => 1.645,
3 => 1.960,
4 => 2.128,
5 => 2.241,
6 => 2.326,
7 => 2.394,
8 => 2.450,
9 => 2.498,
10 => 2.539,
24 => 3.5
};

$CD = $qa->{$k}*sqrt(($k*($k+1))/(6*$N));

printf "\nAccording to the Bonferroni-Dunn statistis, the critical value for comparing the mean-ranking of two different algorithms at 90 percentile is %.2f. Mean-rankings differences above this value are significative.\n\n",$CD;

foreach $alg (sort {$avg{$a} <=> $avg{$b}} keys %avg){
	if ($alg eq $control){

	}elsif($avg{$alg} - $avg{$control} >= $CD){
		print "$alg is worst than $control\n";
	}elsif($avg{$control} - $avg{$alg} >= $CD){
		print "$alg is better than $control\n";
	}else{
		print "It is not possible to detect any significant difference between $alg and $control\n";
	}
}

