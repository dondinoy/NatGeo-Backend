
import { isRouteErrorResponse, useRouteError } from 'react-router'
import styles from './error.module.scss'
const ArticleIdError = () => {

  const error=useRouteError();
  let errorMessage='';

  if(error instanceof Error){
    errorMessage=error.message;
  }
   else if (typeof error === "string") {
      errorMessage = error;
    } else if (isRouteErrorResponse(error)) {
      errorMessage = `${error.data} ${error.status} ${error.statusText}`
    }

  return (
    <div className={styles.error}>
      <h1>Oops</h1>
      <p>Invalid Article Id</p>
      <p>
        <i>{errorMessage}</i>
      </p>
    </div>
  )
}

export default ArticleIdError
