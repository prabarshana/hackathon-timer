<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<meta charset="ISO-8859-1">
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="icon" type="image/x-icon" href="images/favicon.ico">
    <!-- Bootstrap CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <!-- main styles -->
    <link href="css/main-styles.css" rel="stylesheet" />
    <!-- google font -->
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600&display=swap"
      rel="stylesheet"
    />
    <title>ZeroDays Hackathon | Kaleris</title>
  </head>
  <body>
    <div class="container vh-100">
      <div class="row h-100-">
        <div class="col-12">
          <img
            src="images/KALERIS-Logo-greenWhite.svg"
            alt="Kaleris Logo"
            class="kaleris-logo img-fluid"
          />
        </div>
        <div class="col-12">
          <div class="separator"></div>
        </div>
        <div class="col-12">
          <img
            src="images/logo-white.png"
            alt="Kaleris Logo"
            class="zerodays-logo img-fluid"
          />
        </div>
        <div class="col-12 mt-5">
          <div>
            <p id="timer"></p>
          </div>
        </div>
        <div class="col-12">
          <div class="d-flex justify-content-center">
          <form action="/update-flag" id="timer_form">
          <button id="ts1" type="button" class="btn btn-kaleris me-3">Start</button>
          <button id = "te1" type="button" class="btn btn-kaleris-secondary" onclick="timer_stop()">Stop</button>
          </form>
          </div>
        </div>
        <div class="col-12 footer">
          <p>Innovation | Performance | Productivity</p>
        </div>
      </div>
    </div>
    <!-- Timer Script -->
    <script>
    

      const start = new Date("${start_date}");
      const end = new Date("${end_date}");
      
    
      var timer_id;
      // Set the date we're counting down to
     // var countDownDate = new Date("Jan 31, 2022 15:37:25").getTime();
      var countDownDate = end.getTime();

      var func = function () {
        // Get today's date and time
        var now = new Date().getTime();

        // Find the distance between now and the count down date
        var distance = countDownDate - now;

        // Time calculations for days, hours, minutes and seconds
        var days = Math.floor(distance / (1000 * 60 * 60 * 24));
        var hours = Math.floor(
          (distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
        );
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

        // Output the result in an element with id="demo"
        document.getElementById("timer").innerHTML =
          days + "d " + hours + "h " + minutes + "m " + seconds + "s ";

        // If the count down is over, write some text
        if (distance < 0) {
          clearInterval(timer_id);
          document.getElementById("timer").innerHTML = "Time Over!!";
        }
      };
      
      

      // Update the count down every 1 second

      function timer_start() {
        timer_id = setInterval(func, 1000);
        document.getElementById('ts1').style.display =  "none";
        document.getElementById('te1').style.display =  "none";
      }

      function timer_stop() {
        clearInterval(timer_id);
        document.getElementById("timer").innerHTML = "Time's Up!!";
      }
      
      
      var event_status =  "${event_status}";
      var button_status =  "${button_status}";
      
      if ("S" == event_status || "CLICKED" == button_status)	{
    	  document.getElementById('ts1').style.display =  "none";
    	  document.getElementById('te1').style.display =  "none";
    	  timer_start();
      } else if ("E" == event_status)	{
    	  document.getElementById("timer").innerHTML = "Time's Up!!";
      } else if ("B" == event_status)	{
    	  document.getElementById("timer").innerHTML =
              10 + "d " + 0 + "h " + 0 + "m " + 0 + "s ";
      }
      
      
      $( "#ts1" ).click(function() {
    	  
    	  $.post("/update-flag",
    			  {
    			    flag: "Y",
    			    timeStamp: new Date()
    			  },
    			  function(data, status){
    			    
    				  if ("success" == status) {
    					  timer_start();
    				  }
    				  
    			  });
    	  
      });
      
    </script>

    <!-- Bootstrap Bundle with Popper -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
