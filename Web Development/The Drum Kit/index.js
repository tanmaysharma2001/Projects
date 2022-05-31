for(var i = 0; i < document.querySelectorAll(".drum").length; i++) {

  document.querySelectorAll(".drum")[i].addEventListener("click", function () {

      var buttonInnerHTML = this.innerHTML;

      makeSound(buttonInnerHTML);

      butttonAnimation(buttonInnerHTML);

  });

}

document.addEventListener("keypress", function(event) {
  makeSound(event.key);
  butttonAnimation(event.key);
});

function makeSound(key) {
  var src = "sounds/";
  switch (key) {
    case "w": src = src + "tom-1.mp3";
      break;

    case "a": src = src + "tom-2.mp3";
      break;

    case "s": src = src + "tom-3.mp3";
      break;

    case "d": src = src + "tom-4.mp3";
      break;

    case "j": src = src + "snare.mp3";
      break;

    case "k": src = src + "crash.mp3";
      break;

    case "l": src = src + "kick-bass.mp3";
      break;

    default: console.log();

  }

  var audio = new Audio(src);
  audio.play();
};

function butttonAnimation(currentKey) {
  var activeButton = document.querySelector("." + currentKey);

  activeButton.classList.add("pressed");

  setTimeout(function() {
    activeButton.classList.remove("pressed");
  }, 100);
};
