import { Outlet } from "react-router";
import Footer from "../../component/footer/Footer";
import Navbar from "../../component/navbar/Navbar";
const Root = () => {
  
  return (
    <div className="min h-screen flex flex-col ">
      <Navbar/>
        <main className="flex-1">
          <Outlet></Outlet>
        </main>
      <Footer />
    </div>

  );

};


export default Root;