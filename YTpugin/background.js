chrome.runtime.onMessage.addListener(function(res, sender, sendRes) {
  chrome.tabs.executeScript(null, {
    code: "document.getElementsByTagName('video')[0].playbackRate = " + res
    // file: "videoNav.js"
  });
});
