window.onload = function() {
  var el = document.getElementById('speedUp'),
      speed;

  el.addEventListener('click', function(){
    speed = document.getElementById('speedInfo').value;
    chrome.runtime.sendMessage(speed);
  }, false);

}
