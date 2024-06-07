import { BarLoader, BounceLoader, CircleLoader, ClipLoader, ClockLoader, DotLoader, GridLoader, RingLoader } from 'react-spinners'

import './spinner.scss'
const Spinner = () => {
  return (
    <div className="flex flex-col justify-center items-center min-h-screen w-full text-center">
      <div><GridLoader className="flex flex-col  w-full"	color="black" speedMultiplier={2}/></div>
    </div>
  )
}
export default Spinner
