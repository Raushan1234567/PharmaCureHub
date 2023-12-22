
function pay() {
    let amount=document.getElementById("amount").value;

    $.ajax(
    {
    url:`http://localhost:9090/cart/payment/${amount}`,
    data:JSON.stringify({amount:amount,info:"information"}),
    contentType:'appliation/json',
    type:'Post',
    dataType:'json',
    success:function(response) {
    
    if(response.status == "created") {
        let options = {
            key: 'rzp_test_QHOBobX1qODW9l',
            amount: response.amount,
            currency: 'INR',
            name: "Demo",
            description: "Test Transaction",
            image: "../Image/Pharmacurehub2.png",
            order_id: response.id,
            handler:function(response){
    console.log(response.razorpay_payement_id);
    console.log(response.razorpay_order_id);
    console.log(response.razorpay_signature);console.log("payment done");
            },
            prefill:{
                name:'',
                email:'',
                contact:''
            },
            notes: {
                address: "Razorpay Corporate Office Test"
            },
            
        theme: {
            color: "#3399cc"
        }
         }
         var rzp1 = new Razorpay(options);
         rzp1.on("payment.failed",function(response){
            console.log(response);
            alert("failed");
         });
    
         rzp1.open();
        
    }
    
    },
    error:function(error){
        console.log(error); 
    }
    
    })
    }