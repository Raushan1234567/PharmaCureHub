document.addEventListener('DOMContentLoaded', function () {
    const slides = document.querySelectorAll('.slide');
    let currentSlide = 0;

    function showSlide(index) {
      slides.forEach((slide, i) => {
        slide.style.opacity = i === index ? 1 : 0;
      });
    }

    function nextSlide() {
      currentSlide = (currentSlide + 1) % slides.length;
      showSlide(currentSlide);
    }

    setInterval(nextSlide, 2000); // Change the duration (in milliseconds) as needed
  });
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


