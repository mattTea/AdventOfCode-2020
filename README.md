Advent of Code 2020
===================

## Day 1

### Part 1

Find the two entries that sum to 2020 and then multiply those two numbers together.

For example, suppose your expense report contained the following:
```
1721
979
366
299
675
1456
```

In this list, the two entries that sum to 2020 are 1721 and 299.
Multiplying them together produces 1721 * 299 = 514579, so the correct answer is 514579.

Of course, your expense report is much larger.
Find the two entries that sum to 2020; what do you get if you multiply them together?

### Part 2

Find the three entries that sum to 2020; what do you get if you multiply them together?

------

## Day 2

### Part 1

Their password database seems to be a little corrupted:
some of the passwords wouldn't have been allowed by the Official Toboggan Corporate Policy that was in effect when they were chosen.

To try to debug the problem, they have created a list (your puzzle input)
of passwords (according to the corrupted database)
and the corporate policy when that password was set.

For example, suppose you have the following list:
```
1-3 a: abcde
1-3 b: cdefg
2-9 c: ccccccccc
```

Each line gives the password policy and then the password.
The password policy indicates the lowest and highest number of times a given letter must appear for the password to be valid.
For example, 1-3 a means that the password must contain `a` at least 1 time and at most 3 times.

In the above example, 2 passwords are valid.
The middle password, cdefg, is not; it contains no instances of b, but needs at least 1.
The first and third passwords are valid: they contain one a or nine c, both within the limits of their respective policies.

How many passwords are valid according to their policies?

### Part 2

Each policy actually describes two positions in the password,
where 1 means the first character,
2 means the second character, and so on.
(Be careful; Toboggan Corporate Policies have no concept of "index zero"!)
Exactly one of these positions must contain the given letter.
Other occurrences of the letter are irrelevant for the purposes of policy enforcement.

Given the same example list from above:

- `1-3 a: abcde` is valid: position 1 contains a and position 3 does not.
- `1-3 b: cdefg` is invalid: neither position 1 nor position 3 contains b.
- `2-9 c: ccccccccc` is invalid: both position 2 and position 9 contain c.

How many passwords are valid according to the new interpretation of the policies?

------

## Day 3

### Part 1

The toboggan can only follow a few specific slopes (you opted for a cheaper model that prefers rational numbers); start by counting all the trees you would encounter for the slope right 3, down 1:

From your starting position at the top-left, check the position that is right 3 and down 1. Then, check the position that is right 3 and down 1 from there, and so on until you go past the bottom of the map.

The locations you'd check in the above example are marked here with O where there was an open square and X where there was a tree:
```
..##.........##.........##.........##.........##.........##.......  --->
#..O#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
.#....X..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
..#.#...#O#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
.#...##..#..X...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
..#.##.......#.X#.......#.##.......#.##.......#.##.......#.##.....  --->
.#.#.#....#.#.#.#.O..#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
.#........#.#........X.#........#.#........#.#........#.#........#
#.##...#...#.##...#...#.X#...#...#.##...#...#.##...#...#.##...#...
#...##....##...##....##...#X....##...##....##...##....##...##....#
.#..#...#.#.#..#...#.#.#..#...X.#.#..#...#.#.#..#...#.#.#..#...#.#  --->
In this example, traversing the map using this slope would cause you to encounter 7 trees.
```

Starting at the top-left corner of your map and following a slope of right 3 and down 1, how many trees would you encounter?

### Part 2

Time to check the rest of the slopes - you need to minimize the probability of a sudden arboreal stop, after all.

Determine the number of trees you would encounter if, for each of the following slopes, you start at the top-left corner and traverse the map all the way to the bottom:

- Right 1, down 1.
- Right 3, down 1. (This is the slope you already checked.)
- Right 5, down 1.
- Right 7, down 1.
- Right 1, down 2.

In the above example, these slopes would find 2, 7, 3, 4, and 2 tree(s) respectively; multiplied together, these produce the answer 336.

What do you get if you multiply together the number of trees encountered on each of the listed slopes?

------

## Day 4

### Part 1

The automatic passport scanners are slow because they're having trouble detecting which passports have all required fields. The expected fields are as follows:
```
byr (Birth Year)
iyr (Issue Year)
eyr (Expiration Year)
hgt (Height)
hcl (Hair Color)
ecl (Eye Color)
pid (Passport ID)
cid (Country ID)
```

Passport data is validated in batch files (your puzzle input).
Each passport is represented as a sequence of `key:value` pairs separated by spaces or newlines.
Passports are separated by blank lines.

Here is an example batch file containing four passports:
```
ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in
```

The first passport is valid - all eight fields are present.
The second passport is invalid - it is missing `hgt` (the Height field).

The third passport is interesting; the only missing field is cid, so it looks like data from North Pole Credentials, not a passport at all!
Surely, nobody would mind if you made the system temporarily ignore missing cid fields.
Treat this "passport" as valid.

The fourth passport is missing two fields, `cid` and `byr`. Missing `cid` is fine, but missing any other field is not, so this passport is invalid.

According to the above rules, your improved system would report 2 valid passports.

Count the number of valid passports - those that have all required fields.
Treat cid as optional.
In your batch file, how many passports are valid?