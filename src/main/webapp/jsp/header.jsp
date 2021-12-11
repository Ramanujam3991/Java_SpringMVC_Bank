<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#"><img src="/LambtonBank/resources/images/logo.svg" width=50 height=50></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/LambtonBank/Dashboard">Dashboard</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/LambtonBank/transferMoney">Fund Transfer</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="/LambtonBank/payBills">Pay Bills</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/LambtonBank/depositMoney">Deposit</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/LambtonBank/withdrawMoney">Withdraw</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/LambtonBank/forex">Forex Transactions</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="/LambtonBank/addAccount">Add Account</a>
      </li>
     
      
    </ul>
    <span style="width:30%;text-align:right"><c:if test="${session.getAttribute("fullname") != null}">
											   Hello <%=session.getAttribute("fullname")%> 
											</c:if>
  											</span>
    <span style="width:5%;text-align:right"><a class="nav-link" href="/LambtonBank/logout">Logout</a></span>
  </div>
</nav>
