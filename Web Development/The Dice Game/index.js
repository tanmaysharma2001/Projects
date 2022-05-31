// Random Number 1
var randomNumber1 = Math.random();

randomNumber1 = randomNumber1 * 6;

randomNumber1 = Math.floor(randomNumber1) + 1;

// Random Number 2
var randomNumber2 = Math.random();

randomNumber2 = randomNumber2 * 6;

randomNumber2 = Math.floor(randomNumber2) + 1;


var src1 = "images/dice" + randomNumber1 + ".png";

var src2 = "images/dice" + randomNumber2 + ".png";

document.querySelectorAll("img")[0].setAttribute("src", src1);

document.querySelectorAll("img")[1].setAttribute("src", src2);


if(randomNumber1 > randomNumber2) {
  document.querySelectorAll("h1")[0].innerHTML = "⛳Player 1 Wins!"
}
else if(randomNumber2 > randomNumber1) {
  document.querySelectorAll("h1")[0].innerHTML = "Player 2 Wins!⛳"
}
else {
  document.querySelectorAll("h1")[0].innerHTML = "Match is Drawn!";
}
