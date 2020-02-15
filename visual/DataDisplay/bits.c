/* 
 * CS:APP Data Lab 
 * 
 * Skyler Vestal
 * skv458
 * 
 * bits.c - Source file with your solutions to the Lab.
 *          This is the file you will hand in to your instructor.
 *
 * WARNING: Do not include the <stdio.h> header; it confuses the dlc
 * compiler. You can still use printf for debugging without including
 * <stdio.h>, although you might get a compiler warning. In general,
 * it's not good practice to ignore compiler warnings, but in this
 * case it's OK.  
 */

#if 0
/*
 * Instructions to Students:
 *
 * STEP 1: Read the following instructions carefully.
 */

You will provide your solution to the Data Lab by
editing the collection of functions in this source file.

INTEGER CODING RULES:
 
  Replace the "return" statement in each function with one
  or more lines of C code that implements the function. Your code 
  must conform to the following style:
 
  int Funct(arg1, arg2, ...) {
      /* brief description of how your implementation works */
      int var1 = Expr1;
      ...
      int varM = ExprM;

      varJ = ExprJ;
      ...
      varN = ExprN;
      return ExprR;
  }

  Each "Expr" is an expression using ONLY the following:
  1. Integer constants 0 through 255 (0xFF), inclusive. You are
      not allowed to use big constants such as 0xffffffff.
  2. Function arguments and local variables (no global variables).
  3. Unary integer operations ! ~
  4. Binary integer operations & ^ | + << >>
    
  Some of the problems restrict the set of allowed operators even further.
  Each "Expr" may consist of multiple operators. You are not restricted to
  one operator per line.

  You are expressly forbidden to:
  1. Use any control constructs such as if, do, while, for, switch, etc.
  2. Define or use any macros.
  3. Define any additional functions in this file.
  4. Call any functions.
  5. Use any other operations, such as &&, ||, -, or ?:
  6. Use any form of casting.
  7. Use any data type other than int.  This implies that you
     cannot use arrays, structs, or unions.

 
  You may assume that your machine:
  1. Uses 2s complement, 32-bit representations of integers.
  2. Performs right shifts arithmetically.
  3. Has unpredictable behavior when shifting if the shift amount
     is less than 0 or greater than 31.


EXAMPLES OF ACCEPTABLE CODING STYLE:
  /*
   * pow2plus1 - returns 2^x + 1, where 0 <= x <= 31
   */
  int pow2plus1(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     return (1 << x) + 1;
  }

  /*
   * pow2plus4 - returns 2^x + 4, where 0 <= x <= 31
   */
  int pow2plus4(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     int result = (1 << x);
     result += 4;
     return result;
  }

FLOATING POINT CODING RULES

For the problems that require you to implement floating-point operations,
the coding rules are less strict.  You are allowed to use looping and
conditional control.  You are allowed to use both ints and unsigneds.
You can use arbitrary integer and unsigned constants. You can use any arithmetic,
logical, or comparison operations on int or unsigned data.

You are expressly forbidden to:
  1. Define or use any macros.
  2. Define any additional functions in this file.
  3. Call any functions.
  4. Use any form of casting.
  5. Use any data type other than int or unsigned.  This means that you
     cannot use arrays, structs, or unions.
  6. Use any floating point data types, operations, or constants.


NOTES:
  1. Use the dlc (data lab checker) compiler (described in the handout) to 
     check the legality of your solutions.
  2. Each function has a maximum number of operations (integer, logical,
     or comparison) that you are allowed to use for your implementation
     of the function.  The max operator count is checked by dlc.
     Note that assignment ('=') is not counted; you may use as many of
     these as you want without penalty.
  3. Use the btest test harness to check your functions for correctness.
  4. Use the BDD checker to formally verify your functions
  5. The maximum number of ops for each function is given in the
     header comment for each function. If there are any inconsistencies 
     between the maximum ops in the writeup and in this file, consider
     this file the authoritative source.

/*
 * STEP 2: Modify the following functions according the coding rules.
 * 
 *   IMPORTANT. TO AVOID GRADING SURPRISES:
 *   1. Use the dlc compiler to check that your solutions conform
 *      to the coding rules.
 *   2. Use the BDD checker to formally verify that your solutions produce 
 *      the correct answers.
 */


#endif
/* Copyright (C) 1991-2018 Free Software Foundation, Inc.
   This file is part of the GNU C Library.

   The GNU C Library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 2.1 of the License, or (at your option) any later version.

   The GNU C Library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Lesser General Public License for more details.

   You should have received a copy of the GNU Lesser General Public
   License along with the GNU C Library; if not, see
   <http://www.gnu.org/licenses/>.  */
/* This header is separate from features.h so that the compiler can
   include it implicitly at the start of every compilation.  It must
   not itself include <features.h> or any other header that includes
   <features.h> because the implicit include comes before any feature
   test macros that may be defined in a source file before it first
   explicitly includes a system header.  GCC knows the name of this
   header in order to preinclude it.  */
/* glibc's intent is to support the IEC 559 math functionality, real
   and complex.  If the GCC (4.9 and later) predefined macros
   specifying compiler intent are available, use them to determine
   whether the overall intent is to support these features; otherwise,
   presume an older compiler has intent to support these features and
   define these macros by default.  */
/* wchar_t uses Unicode 10.0.0.  Version 10.0 of the Unicode Standard is
   synchronized with ISO/IEC 10646:2017, fifth edition, plus
   the following additions from Amendment 1 to the fifth edition:
   - 56 emoji characters
   - 285 hentaigana
   - 3 additional Zanabazar Square characters */
/* We do not support C11 <threads.h>.  */
//1
/* 
 * bitXor - x^y using only ~ and & 
 *   Example: bitXor(4, 5) = 1
 *   Legal ops: ~ &
 *   Max ops: 14
 *   Rating: 1
 */
int bitXor(int x, int y) {
  // If either y is true and x is false or x is true and y is false return true
  // The ~(...) is applying demorgans to ((~x & y) | (x & ~y))
  return ~(~(~x & y) & ~(x & ~y));
}
/* 
 * upperBits - pads n upper bits with 1's
 *  You may assume 0 <= n <= 32
 *  Example: upperBits(4) = 0xF0000000
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 10
 *  Rating: 1
 */
int upperBits(int n) {
  // Take a full string of 1s and shift it to the right n times (shiftNum is 32 - n)
  int oneString = ~0;
  int shiftNum = 32 + (~n + 1);
  // The divsion of shiftNum is to avoid the undefined operation of 32
  oneString = oneString << (shiftNum >> 1);
  oneString = oneString << ((shiftNum + 1) >> 1);
  return oneString;
}
//2
/* 
 * byteSwap - swaps the nth byte and the mth byte
 *  Examples: byteSwap(0x12345678, 1, 3) = 0x56341278
 *            byteSwap(0xDEADBEEF, 0, 2) = 0xDEEFBEAD
 *  You may assume that 0 <= n <= 3, 0 <= m <= 3
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 25
 *  Rating: 2
 */
int byteSwap(int x, int n, int m) {
  // Grab a evenMask of the byte n and m (0 - 3)
  // First multiply n and m by 8 (<< 3) in order to get the total bits to shift by
  int nAdj = n << 3;
  int mAdj = m << 3;
  // Obtain a evenMask of the currently selected bit
  int nevenMask = (0xFF << nAdj) & x;
  int mevenMask = (0xFF << mAdj) & x;
  // Zero out the current occupied byte in x
  x = x ^ nevenMask;
  x = x ^ mevenMask;
  // Shift nevenMask to where mevenMask is and vice versa
  // evenMask with 0xFF to remove logical shifting
  nevenMask = ((nevenMask >> nAdj) << mAdj) & (0xFF << mAdj);
  mevenMask = ((mevenMask >> mAdj) << nAdj) & (0xFF << nAdj);
  // Insert swapped bytes
  x = x | nevenMask;
  x = x | mevenMask;
  return x;
}
/* 
 * implication - return x -> y in propositional logic - 0 for false, 1
 * for true
 *   Example: implication(1,1) = 1
 *            implication(1,0) = 0
 *   Legal ops: ! ~ ^ |
 *   Max ops: 5
 *   Rating: 2
 */
int implication(int x, int y) {
  // If x is false the implication is true, otherwise depends on y being true
  return !x | y;
}
/* 
 * sign - return 1 if positive, 0 if zero, and -1 if negative
 *  Examples: sign(130) = 1
 *            sign(-23) = -1
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 10/.
 *  Rating: 2
 */
int sign(int x) {
  // If the sign bit is 0 let signBit be 1
  int signBit = !(x & (1 << 31));
  // Start with -2 (~1), then add the sign bit twice. 
  // Now both 0 and positive numbers have values 0 and negative has a value -2
  // Add 1 to the expression if x is non-zero to create the range of -1, 0, 1
  return ~1 + signBit + signBit + !!x;
}
/* 
 * anyEvenBit - return 1 if any even-numbered bit in word set to 1
 *   Examples anyEvenBit(0xA) = 0, anyEvenBit(0xE) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 2
 */
int anyEvenBit(int x) {
  // evenMask is 1010 for even bits. Add this to the other three bytes
  int evenMask = 85;
  evenMask = evenMask + (evenMask << 8) + (evenMask << 16) + (evenMask << 24);
  // See if any bit exists after being evenMasked with the even evenMask
  return !!(x & evenMask);
}
//3
/* 
 * addOK - Determine if can compute x+y without overflow
 *   Example: addOK(0x80000000,0x80000000) = 0,
 *            addOK(0x80000000,0x70000000) = 1, 
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 3
 */
int addOK(int x, int y) {
  int sum = x + y;
  // See if the sign of x and y changed. If it did and the sum is different than the 
  // sign of x then the add is bad so inverse the result
  int ovf = !(x >> 31 ^ y >> 31) & (x >> 31 ^ sum >> 31);
  return !ovf;
}
/* 
 * conditional - same as x ? y : z 
 *   Example: conditional(2,4,5) = 4
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 16
 *   Rating: 3
 */
int conditional(int x, int y, int z) {
  // If x is non-zero make a oneString
  int xOneMask = (((!!x) << 31) >> 31);
  // If x is true the mask will allow y exlucsively to be returned
  // otherwise z will be returned
  int yRes = xOneMask & y;
  int zRes = ~xOneMask & z;
  return yRes | zRes;
}
/* 
 * rotateLeft - Rotate x to the left by n
 *   Can assume that 0 <= n <= 31
 *   Examples: rotateLeft(0x87654321,4) = 0x76543218
 *   Legal ops: ~ & ^ | + << >> !
 *   Max ops: 25
 *   Rating: 3 
 */
int rotateLeft(int x, int n) {
  // Obtaint he number of shifting right later
  int rShift = 32 + (~n + 1);
  int wrappedX = x;
  // Save the first bit and extract it to avoid logical shift complications
  int firstBit = 1 << 31;
  firstBit = !!(firstBit & wrappedX);
  firstBit = firstBit << (n + (~1 + 1));
  // Clear the extra values from wrappedX and then move it to the appropriate place
  wrappedX = wrappedX & ~(1 << 31);
  wrappedX = wrappedX >> rShift;
  // Shift right side to the approrpiate place
  x = x << n;
  // Add every string together
  return x | wrappedX | firstBit;
}
/*
 * satMul2 - multiplies by 2, saturating to Tmin or Tmax if overflow
 *   Examples: satMul2(0x30000000) = 0x60000000
 *             satMul2(0x40000000) = 0x7FFFFFFF (saturate to TMax)
 *             satMul2(0x80000001) = 0x80000000 (saturate to TMin)
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 3
 */
int satMul2(int x) {
  // Get a bit mask for the first bit
  int firstBitMask = 1 << 31;
  // Multiply x by 2
  int multX = x << 1;
  // Get the original first bit of x
  int orgBit = x & firstBitMask;
  // Get the multiplied first bit of x
  int multBit = multX & firstBitMask;
  // See if th ebits are different
  int diff = orgBit ^ multBit;
  // Fill with 1s if it's the case they are
  int diffMask = diff >> 31;
  // Fill a mask with every bit but the first
  int lastMask = ~(1 << 31);
  // Return multX if diffMask ended up not being different, otherwise
  // return if the originalBit was non-zero to create either tMin or tMax
  return (multX & ~diffMask) | (diffMask & (!!orgBit + lastMask));
}
//4
/*
 * bitReverse - Reverse bits in a 32-bit word
 *   Examples: bitReverse(0x80000002) = 0x40000001
 *             bitReverse(0x89ABCDEF) = 0xF7D3D591
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 45
 *   Rating: 4
 */
int bitReverse(int x) {
  // Start with a blank slate
  int res = 0;
  // Grab the first half of the int
  int multiMask = 0xFF | 0xFF << 8;
  int temp;
  // Flip the two halves of the byte
  res = (x >> 16 & multiMask) | (x << 16);
  // Grab every other byte
  multiMask = 0xFF | (0xFF << 16);
  // Flip the bytes
  temp = (multiMask & res) << 8 | (res >> 8 & multiMask);
  // Grab every other nybble 
  multiMask = 0xF | 0xF << 8;
  multiMask = multiMask | multiMask << 16;
  // Flip the nybbles
  res = (multiMask & temp) << 4 | (temp >> 4 & multiMask);
  // Grab every pair of bits
  multiMask = 0x33 | 0x33 << 8;
  multiMask = multiMask | multiMask << 16;
  // Flip the pairs of bits
  temp = (multiMask & res) << 2 | (res >> 2 & multiMask);
  // Grab every other bit
  multiMask = 0x55 | 0x55 << 8;
  multiMask = multiMask | multiMask << 16;
  // Flip the bits
  res = (multiMask & temp) << 1 | (temp >> 1 & multiMask);
  return res;
}
/*
 * isPower2 - returns 1 if x is a power of 2, and 0 otherwise
 *   Examples: isPower2(5) = 0, isPower2(8) = 1, isPower2(0) = 0
 *   Note that no negative number is a power of 2.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 4
 */
int isPower2(int x) {
  // If only one bit is active, then the number plus the negative of the number shouldn't
  // overlap at all.
  int subOne = x + (~1 + 1);
  // Exclude negatives and 0
  int signBit = (1 << 31) & x;
  return !(x & subOne) & !!x & !signBit;
}
//float
/* 
 * floatAbsVal - Return bit-level equivalent of absolute value of f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representations of
 *   single-precision floating point values.
 *   When argument is NaN, return argument..
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 10
 *   Rating: 2
 */
unsigned floatAbsVal(unsigned uf) {
  // Grab the exponent and mantissa
  unsigned exp = (uf >> 23) & 0xFF;
  unsigned mantissa = uf & 0x7FFFFF;
  // Detect if the numebr is NaN
  if (exp == 0xFF && mantissa != 0) {
    return uf;
  }
  // Otherwise make the number positive if the bit is negative
  return (uf << 1) >> 1;
}
/* 
 * floatInt2Float - Return bit-level equivalent of expression (float) x
 *   Result is returned as unsigned int, but
 *   it is to be interpreted as the bit-level representation of a
 *   single-precision floating point values.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned floatInt2Float(int x) {
  // Get the positive version of x
  int rX = x < 0 ? ~x + 1 : x;
  // Get the sign bit taken care of 
  unsigned res = (1 << 31) & x;
  unsigned tmp = rX;
  int count = 0;
  unsigned man;
  int lastEight;
  // Handle edge case
  if (x == 0) {
      return 0;
  }
  // Get the exponent for the value
  while (tmp > 1) {
    tmp = tmp >> 1;
    count++;

  }
  // Isolate the mantissa
  man = rX;
  man = man << (32 - count);
  man = man >> 9;
  // Set the non-rounded float
  res = (res | (count + 127) << 23 | man);
  lastEight = 0xFF & (rX << (31 - count));
  // Have to float only if it's possible that bits were cut off
  if (count >= 24) {
    // Leading bit in cutoff needs to be rounded up
    if (lastEight > 0x80) {
      res++;
    }
    // If mantisa|10000000 have to round to even bit
    if (lastEight == 0x80) {
      if (1 & res) {
        res++;
      }
    }
  }
  return res;
}
/* 
 * floatScale2 - Return bit-level equivalent of expression 2*f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representation of
 *   single-precision floating point values.
 *   When argument is NaN, return argument
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned floatScale2(unsigned uf) {
  // Isolate components of float
  int sign = (1 << 31) & uf;
  unsigned exp = (uf >> 23) & 0xFF;
  unsigned mantissa = uf & 0x7FFFFF;
  // Handle edge case
  if (uf == 0) {
    return 0;
  }
  // If answer is NaN don't worry about it
  if (exp == 0xFF || (exp == 0 && mantissa == 0)) {
    return uf;
  }
  // If exponent is normal just bit shift it over
  if (exp != 0) {
    exp++;
    exp = exp << 23;
  } else {
    // Otherwise we just need to double the mantissa
    uf *= 2;
    return sign | uf;
  }
  // Return combined float
  return sign | exp | mantissa;
}
