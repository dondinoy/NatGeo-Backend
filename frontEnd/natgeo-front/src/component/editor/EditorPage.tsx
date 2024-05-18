/* eslint-disable @typescript-eslint/no-unused-vars */
import { useCallback, useState } from "react";
import {
  BaseEditor,
  Descendant,
  Transforms,
  createEditor,
  Element,
  Editor,
} from "slate";

import {
  DefaultElement,
  Editable,
  ReactEditor,
  RenderElementProps,
  Slate,
  withReact,
} from "slate-react";
import CodeElement from "../../component/editor/CodeElement";
import Leaf from "./Leaf";
import { JSX } from "react/jsx-runtime";


type CustomElement = { type: "paragraph" | "code"; children: CustomText[] };
type CustomText = { text: string };

//type declaration: 
declare module "slate" {
  interface CustomTypes {
    Editor: BaseEditor & ReactEditor;
    Element: CustomElement;
    Text: CustomText;
  }
}
interface RegisterType{
  register:string
  name:string
}
const EditorPage :React.FC<RegisterType>=({register, name})=>{
// const EditorPage = ({register, name}) => {
  // Create a Slate editor object that won't change across renders.
  const [editor] = useState(() => withReact(createEditor()));

  const renderElement = useCallback((props: JSX.IntrinsicAttributes & RenderElementProps) => {
    switch (props.element.type) {
      case "code":
        return <CodeElement {...props} />;
      default:
        return <DefaultElement {...props} />;
    }
  }, []);

  // Define a leaf rendering function that is memoized with `useCallback`.
  const renderLeaf = useCallback((props) => {
    return <Leaf {...props} />;
  }, []);

  const CustomEditor = {
    isBoldMarkActive(editor) {
      const marks = Editor.marks(editor);
      return marks ? marks.bold === true : false;
    },
    toggleBoldMark(editor) {
      const isActive = CustomEditor.isBoldMarkActive(editor);
      if (isActive) {
        Editor.removeMark(editor, "bold");
      } else {
        Editor.addMark(editor, "bold", true);
      }
    },

    isItalicMarkActive(editor) {
      const marks = Editor.marks(editor);
      return marks ? marks.italic === true : false;
    },

    toggleItalicMark(editor) {
      const isActive = CustomEditor.isItalicMarkActive(editor);
      if (isActive) {
        Editor.removeMark(editor, "italic");
      } else {
        Editor.addMark(editor, "italic", true);
      }
    },

    isFontSizeActive(editor, size) {
      const marks = Editor.marks(editor);
      return marks ? marks.size === size : false;
    },
    toggleFontSize(editor, size) {
      const isActive = CustomEditor.isFontSizeActive(editor, size);
      if (isActive) {
        Editor.removeMark(editor, "size");
      } else {
        Editor.addMark(editor, "size", size);
      }
    },
  };

  const initialValue: Descendant[] = [
    {
      type: "paragraph",
      children: [{ text: "A line of text in a paragraph." }],
    },
  ];
  return (
    <Slate editor={editor} 
           initialValue={initialValue} 
           >
      <div>   
        <button
          onMouseDown={(event) => {
            event.preventDefault();
            CustomEditor.toggleBoldMark(editor);
          }}
        >
          Bold
        </button>
        <button
          onMouseDown={(event) => {
            event.preventDefault();
            CustomEditor.toggleItalicMark(editor);
          }}
        >
          Italic
        </button>
        <div>
          <button
            onMouseDown={(event) => {
              event.preventDefault();
              CustomEditor.toggleFontSize(editor, 16); // Change 16 to desired font size
            }}
          >
            Small
          </button>
          <button
            onMouseDown={(event) => {
              event.preventDefault();
              CustomEditor.toggleFontSize(editor, 20); // Change 20 to desired font size
            }}
          >
            Medium
          </button>
          <button
            onMouseDown={(event) => {
              event.preventDefault();
              CustomEditor.toggleFontSize(editor, 24); // Change 24 to desired font size
            }}
          >
            Large
          </button>
        </div>
      </div>
      <Editable 
        className="flex flex-col shadow-xl m-5 max-w-100 h-52"
        editor={editor}
        {...register(name)}
        renderElement={renderElement}
        renderLeaf={renderLeaf}
        onKeyDown={(event) => {
          if (!event.ctrlKey) {
            return;
          }

          switch (event.key) {
            case "b": {
              event.preventDefault();
              CustomEditor.toggleBoldMark(editor);
              break;
            }
          }
        }}
      />
    </Slate>
  );
};

export default EditorPage;
