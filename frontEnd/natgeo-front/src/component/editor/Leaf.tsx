const Leaf = (props) => {
  return (
    <span
      {...props.attributes}
      style={{ fontWeight: props.leaf.bold ? 'bold' : 'normal' ,
              fontStyle: props.leaf.italic ? 'italic' : 'normal',
              fontSize: `${props.leaf.size}px`
    }}
    >
      {props.children}
    </span>
  )
}
export default Leaf;