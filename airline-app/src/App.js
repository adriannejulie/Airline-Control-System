import { Route, Routes , Navigate } from 'react-router-dom';
import { useState } from 'react';
import Login from './Component/Login';
import Customer from './Component/Customer';
import PrivateRoutes from './utils/PrivateRoutes';
import Home from './Component/Home';
import Account from './Component/Account';
import Staff from './Component/Staff';
import CrewLogin from './Component/CrewLogin';

import './App.css';


function App() {
  const [customerlg, setCustomerlg] = useState(false);
  const [stafflg, setStafflg] = useState(false);
  const [customerInfo, setCustomerInfo] = useState({
    name: "Santiago Fuentes",
    email: "santigf12@gmail.com",
    password: "1234567",
    dob: "1999-12-12",
    phoneNumber: "788-123-4567",
    membership: true
  });
  const [staffLgInfo, setStaffLgInfo] = useState("");

  console.log(staffLgInfo.data);


  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/account/create" element={<Account />} />
        <Route path="/login" element={<Login setCustomerlg={setCustomerlg} setCustomerInfo={setCustomerInfo}/>} />
        <Route path="/crew/login" element={<CrewLogin setStafflg={setStafflg} setStaffLgInfo={setStaffLgInfo}/>} />

        <Route element={<PrivateRoutes isAuthenticated={stafflg}/>}>
          <Route path="/staff" element={<Staff setStafflg={setStafflg} staffLgInfo={staffLgInfo} />} />
        </Route>
       
        
        <Route element={<PrivateRoutes isAuthenticated={customerlg}/>}>
          <Route path="/flights/customer" element={<Customer customerInfo={customerInfo} setCustomerlg={setCustomerlg}/>} />
        </Route>
        
        <Route exact path="/*" element={<Navigate to={"/"}/>} />
      </Routes>
    </>
  );
}

export default App;
