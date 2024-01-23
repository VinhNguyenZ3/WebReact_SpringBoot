import React, { useRef } from "react";
import {
  List,
  Datagrid,
  TextField,
  Edit,
  SimpleForm,
  EditButton,
  TextInput,
  NumberInput,
  Create,
  ReferenceInput,
  SelectInput,
  useNotify,
  useRedirect,
} from "react-admin";

import ImageUploadForm from "./ImageUploadForm";

export const listProduct = (props) => (
  <List {...props}>
    <Datagrid style={{ overflowX: "auto" }}>
      <TextField source="id" />
      <TextField source="name" />
      <TextField source="description" />
      <TextField source="photo" />
      <TextField source="price" />
      <TextField source="category.title" />
      <TextField source="brand.name" />
      <EditButton />
    </Datagrid>
  </List>
);

export const editProduct = (props) => {
  return (
    <Edit {...props}>
      <SimpleForm>
        <TextInput source="name" />
        <TextInput source="photo" />
        <NumberInput source="price" />
        <TextInput source="description" multiline fullWidth />
        <ReferenceInput
          label="Category"
          source="category.id"
          reference="categories"
        >
          <SelectInput optionText="title" />
        </ReferenceInput>
        <ReferenceInput
          label="Brand"
          source="brand.id"
          reference="brands"
        >
          <SelectInput optionText="name" />
        </ReferenceInput>
      </SimpleForm>
    </Edit>
  );
};

export const CreateProduct = (props) => {
  const notify = useNotify();
  const redirect = useRedirect();

  const imageUploadFormRef = useRef();

  const onSuccess = (data) => {
    notify(`created successfully`);

    if (imageUploadFormRef.current) {
      imageUploadFormRef.current.handleImageUpload(data.photo);
    }
    redirect("list", "products");
  };
  return (
    <Create {...props} mutationOptions={{ onSuccess }} redirect="products">
      <SimpleForm>
        <TextInput source="name" />
        <TextInput source="slug" />
        <NumberInput source="price" />
        <TextInput source="detail" />
        <TextInput source="photo" />
        <TextInput source="description" multiline fullWidth />
        <ReferenceInput
          label="Category"
          source="category.id"
          reference="categories"
        >
          <SelectInput optionText="title" />
        </ReferenceInput>
        <ReferenceInput
          label="Brand"
          source="brand.id"
          reference="brands"
        >
          <SelectInput optionText="name" />
        </ReferenceInput>
        <ImageUploadForm ref={imageUploadFormRef} />
      </SimpleForm>
    </Create>
  );
};
