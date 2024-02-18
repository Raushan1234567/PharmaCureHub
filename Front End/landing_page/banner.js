// document.addEventListener('DOMContentLoaded', function () {
//   const slides = document.querySelectorAll('.slide');
//   const totalSlides = slides.length;
//   let currentSlide = 0;
//   let animationFrameId;

//   function showSlide(index) {
//     slides.forEach((slide, i) => {
//       slide.style.opacity = i === index ? 1 : 0;
//     });
//   }

//   function nextSlide() {
//     currentSlide = (currentSlide + 1) % totalSlides;
//     showSlide(currentSlide);
//     // Change the duration to 1000 milliseconds (1 second)
//     setTimeout(() => {
//       animationFrameId = requestAnimationFrame(nextSlide);
//     }, 1000);
//   }

//   // Use requestAnimationFrame for smoother animations
//   animationFrameId = requestAnimationFrame(nextSlide);

//   // Stop the animation when the page is hidden or inactive
//   document.addEventListener('visibilitychange', function () {
//     if (document.hidden) {
//       cancelAnimationFrame(animationFrameId);
//     } else {
//       // Resume animation when the page becomes visible
//       animationFrameId = requestAnimationFrame(nextSlide);
//     }
//   });
// });


  document.addEventListener('DOMContentLoaded', () => {
    const user = JSON.parse(localStorage.getItem('user'));

    if (user) {
        const signup = document.getElementById("singupcontent");
        signup.innerText = `Hi, ${user.username}`;
    }
});
let home__logoutCont = document.getElementById("home__logoutCont");
let home__logoutBtn = document.getElementById("home__logoutBtn");

// Event listener for the logout button
home__logoutBtn.addEventListener('click', () => {
    // Fetch the cart ID by customer ID (assuming you need it for cart removal)
    const userString = localStorage.getItem('user');
    let customerId;

    if (userString) {
        const user = JSON.parse(userString);
        customerId = user.customerId;
        localStorage.removeItem('user');

    alert("Logout successful");
    window.location.reload();
    }

    // Remove the user details from localStorage
    
});


