
import { isRouteErrorResponse, useRouteError } from 'react-router';
import styles from './error.module.scss'
import Navbar from '../../component/navbar/Navbar';
import Footer from '../../component/footer/Footer';
const ErrorPage = () => {
   const error=useRouteError();

  let errorMessage='';
  if(error instanceof Error){
    errorMessage=error.message;
  }
   else if (typeof error === "string") {
      errorMessage = error;
    } else if (isRouteErrorResponse(error)) {
      errorMessage = `${error.data} ${error.status} ${error.statusText}`;
    }
    
  return (

    <div>
    <Navbar />
    <header>
       <div className={styles.error}>
      <h1>Oops</h1>
      <p>Sorry an error occured</p>
      <p>
        <i>{errorMessage}</i>
      </p>
    </div>
    </header>
    <Footer />
    </div>
    
  )
}

export default ErrorPage
