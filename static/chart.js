var conn;                       // global
(function () {
  var $i = $('#i'),
      $history = $('#history');

  var max_id = 0;

  conn = new WebSocket("ws://127.0.0.1:9899/ws");

  conn.onopen = function (e) {
    conn.send(JSON.stringify({command: 'getall'}));
  };

  conn.onerror = function () {
    alert("error");
    console.log(arguments);
  };

  conn.onmessage = function (e) {
    var msgs = JSON.parse(e.data);
    for(var i = 0; i < msgs.length; i++) {
      var msg = msgs[i];
      if(msg.id > max_id) {
        add_msg(msg);
        max_id = msg.id;
      }
    }
  };

  function add_msg (msg) {
    var now = Math.round(new Date().getTime() / 1000);
    var t = (now - msg.time) + 's ago';
    t = ["<span class=\"time\">", t + "</span>"].join('');
    var author = ["<span class=\"author\">",
                  msg.author,
                  "</span>: "].join('');
    $history.append('<li>' + author + msg.msg + t +'</li>');
  }

  function send_to_server () {
    var msg = $.trim($i.val()),
        author = $.trim($('#name').val() || 'anonymous');
    if(msg) {
      conn.send(JSON.stringify({msg: msg, author: author}));
      $i.val('');
    }
  }

  $('#send').click(send_to_server);
  $i.focus().keyup(function (e) {
    if(e.which === 13) {        // enter
      send_to_server();
    }
  });
})();


function start_robot (name) {
  var id = 0;

  setInterval(function () {
    var msg = {msg: name + " mesg#" + id, author: name};
    console.log('sending...........', msg);
    conn.send(JSON.stringify(msg));
    id += 1;
  }, 1000);
}