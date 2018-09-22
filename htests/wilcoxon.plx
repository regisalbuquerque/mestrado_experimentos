#!/usr/bin/perl -w

use strict;
use warnings;
use FileHandle;

my $fh = new FileHandle;

my $file = shift;

if (!defined($file)){
   print "USAGE: wilcoxon.pl <datafile>\n";
   exit(0);
}

$fh->open("< $file") or die; 

my $line;

my ($alg1, $alg2);
my ($ds, $res1, $res2);

my %results;

$line = $fh->getline();

chomp($line);

$line =~ s/^\s+//g;

($alg1, $alg2) = split(/\s+/, $line);  


printf "                    \t%s\t%s\t\| diff\trank\n", $alg1, $alg2;

print  "========================================\|=============\n";

while($line = $fh->getline() or not eof($fh)){
  chomp $line;
  if (length($line) > 3){
     ($ds, $res1, $res2) = split(/\s+/, $line);
     $results{$ds}{$alg1} = $res1;
     $results{$ds}{$alg2} = $res2;
     $results{$ds}{diff} = $res1 - $res2;
  }
}

my $rank_pos = 1;
my $rank_value = -1;
my @draw;
my $i;
my $rank_draw;
my $mean_rank;

foreach $ds (sort {abs($results{$a}{diff}) <=> abs($results{$b}{diff})} keys %results){
  if (abs($results{$ds}{diff}) > $rank_value){
     if (scalar(@draw) > 1){
      $mean_rank = ($rank_pos + $rank_draw -1)/2;
      foreach $i (@draw){
        $results{$i}{rank} = $mean_rank;
      }
      undef(@draw);
    }
   $rank_value = abs($results{$ds}{diff});
    $results{$ds}{rank} = $rank_pos;
    @draw = ($ds);
    $rank_draw = $rank_pos;
  }elsif(abs($results{$ds}{diff}) == $rank_value){
    push (@draw, $ds);
  }else{
    die "**************************$rank_value, ";
  }
  $rank_pos++;
} 

if (scalar(@draw) > 1){
  $mean_rank = ($rank_pos + $rank_draw -1)/2;
  foreach $i (@draw){
    $results{$i}{rank} = $mean_rank;
  }
  undef(@draw);
}

foreach $ds (sort keys %results){
  printf "%20s\t%.3f\t%.3f\t\|%.3f\t%.1f\n",
      $ds,
      $results{$ds}{$alg1},
      $results{$ds}{$alg2},
      $results{$ds}{diff},
      $results{$ds}{rank};
}

my ($r_plus, $r_minus);

foreach $ds (keys %results){
  $r_plus += $results{$ds}{rank} if $results{$ds}{diff} > 0;
  $r_minus += $results{$ds}{rank} if $results{$ds}{diff} < 0;

  $r_plus += 0.5*$results{$ds}{rank} if $results{$ds}{diff} == 0;
  $r_minus += 0.5*$results{$ds}{rank} if $results{$ds}{diff} == 0;
}

print "\nSum of ranks for $alg2 is $r_minus\n";
print "Sum of ranks for $alg1 is $r_plus\n";
print "MSE, Error Rate or other measures which lower means better, the sum of ranks for $alg1 is $r_minus and $alg2 is $r_plus)\n";
my $N = scalar keys %results;

if ($N < 6) {die "\nYou should have at least 6 datasets to use this test\n"};

my $wilcoxon = {
6 => 0,
7 => 2,
8 => 3,
9 => 5,
10 => 8,
11 => 10,
12 => 13,
13 => 17,
14 => 21,
15 => 25,
16 => 29,
17 => 34,
18 => 40,
19 => 46,
20 => 52,
21 => 58,
22 => 65,
23 => 73,
24 => 81,
25 => 89,
26 => 98,
27 => 107,
28 => 116,
29 => 126,
30 => 137,
};

  my $T;

  if ($r_plus >= $r_minus){
     $T = $r_minus;
  }else{
     $T = $r_plus;
  }



if ($N < 31){
  print "The p-value for comparing $N datasets at 95\% confiddence level is $wilcoxon->{$N}\n\n";
  if (abs($T) <= $wilcoxon->{$N}){
    print "The null hypothesis that both algorithms are comparable should be rejected.\n";
    print "According to Wilcoxon Signed Test of Ranks, the algorithm with higher sum of ranks is better than the other at 95\% of confidence level.\n";
  }else{
    print "The null hypothesis that both algorithms are comparable should not be rejected.\n";
  }
}else{

  my $z = ($T - .25*$N*($N-1))/sqrt( (1/24)*$N*($N-1)*(2*$N+1) );

  print "The normal approximation for experiments with more than 30 datasets is $z.\n";
  print "The p-value at 95\% confidence level is 1.96.\n";
  if ($z < -1.96){
    print "The null hypothesis that both algorithms are comparable should be rejected.\n";
    print "According to Wilcoxon Signed Test of Ranks, the algorithm with higher sum of ranks is better than the other at 95\% of confidence level.\n";	  
  }else{
    print "The null hypothesis that both algorithms are comparable should not be rejected.\n";    
  }
}

