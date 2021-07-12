
$(document).ready(function(){
    
    //Check to see if the window is top if not then display button
    $(window).scroll(function(){
        if ($(this).scrollTop() > 100) {
            $('.scroll-to-top').fadeIn();
        } else {
            $('.scroll-to-top').fadeOut();
        }
    });
    
    //Click event to scroll to top
    $('.scroll-to-top').click(function(){
        $('html, body').animate({scrollTop : 0},200);
        return false;
    });
    
});

/*$(document).ready(function (argument) {
    var owl = $('.owl-carousel').owlCarousel({
        loop:true,
        margin:20,
        autoplay:true,
        autoplayTimeout:1500,
        autoplayHoverPause:true,
        autoHeight:true,
        responsiveClass:true,
      responsive:{
        0:{
            items:1,
        },
        600:{
          items:3,
          nav:false
        }
      }
    });
    $('.play').on('click',function(){
      owl.trigger('play.owl.autoplay',[3000])
    })
    $('.stop').on('click',function(){
      owl.trigger('stop.owl.autoplay')
    })
  })

if ($(window).width() > 992) {
    $(window).scroll(function(){  
       if ($(this).scrollTop() > 100) {
          $('#navbar_top').addClass("fixed-top");
          // add padding top to show content behind navbar
          $('body').css('padding-top', $('.navbar').outerHeight() + 'px');
        }else{
          $('#navbar_top').removeClass("fixed-top");
           // remove padding top from body
          $('body').css('padding-top', '0');
        }   
    });
  }

*/

function myFunction() {
    if($(window).width() > 500)
    {   
        $('#show_button').hide();
    }
    else
    {
        $('#show_button').show();
    }
}
//initialize
myFunction();

//call when the page resizes.
$(window).resize(function() {
    myFunction();
});

function myFunction() {
    if($(window).width() > 500)
    {   
        $('#hide_button').show();
    }
    else
    {
        $('#hide_button').hide();
    }
}
//initialize
myFunction();

//call when the page resizes.
$(window).resize(function() {
    myFunction();
});


$('.owl-carousel').owlCarousel({
  margin: 4,
  nav: true,
  navText:["<div class='nav-btn prev-slide'><i class='fas fa-chevron-left'></i></div>","<div class='nav-btn next-slide'><i class='fas fa-chevron-right'></i></div>"],
  responsive: {
      0: {
          items: 1
      },
      600: {
          items: 3
      },
      1000: {
          items: 5
      }
  }
});