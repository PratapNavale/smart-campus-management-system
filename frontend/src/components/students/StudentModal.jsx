import { useEffect, useState } from "react";

function StudentModal({
  isOpen,
  onClose,
  onSubmit,
  initialData = null,
}) {

  const isEditMode = !!initialData;

  const getInitialState = () => ({
    userId: "",
    username: "",
    password: "",
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    department: "",
    semester: "",
  });

  const [formData, setFormData] =
    useState(getInitialState());

  useEffect(() => {

    if (initialData) {

      setFormData({
        userId:
          initialData.userId || "",

        firstName:
          initialData.firstName || "",

        lastName:
          initialData.lastName || "",

        email:
          initialData.email || "",

        phone:
          initialData.phone || "",

        department:
          initialData.department || "",

        semester:
          initialData.semester || "",

        username: "",
        password: "",
      });

    } else {

      setFormData(
        getInitialState()
      );

    }

  }, [initialData]);

  if (!isOpen) return null;

  const handleChange = (e) => {

    setFormData({
      ...formData,
      [e.target.name]:
        e.target.value,
    });

  };

  const resetForm = () => {

    setFormData(
      getInitialState()
    );

  };

  const handleSubmit = async (
    e
  ) => {

    e.preventDefault();

    await onSubmit(formData);

    resetForm();

  };

  return (
    <div className="fixed inset-0 bg-black/40 flex justify-center items-center z-50">

      <div className="bg-white w-full max-w-3xl rounded-2xl shadow-xl p-6">

        <h2 className="text-2xl font-bold mb-6">
          {isEditMode
            ? "Edit Student"
            : "Register Student"}
        </h2>

        <form
          onSubmit={handleSubmit}
          className="grid grid-cols-2 gap-4"
        >

          {!isEditMode && (
            <>
              <input
                name="username"
                placeholder="Username"
                value={formData.username}
                onChange={handleChange}
                className="border rounded-xl p-3"
                required
              />

              <input
                name="password"
                type="password"
                placeholder="Password"
                value={formData.password}
                onChange={handleChange}
                className="border rounded-xl p-3"
                required
              />
            </>
          )}

          {isEditMode && (
            <input
              type="hidden"
              name="userId"
              value={formData.userId}
            />
          )}

          <input
            name="firstName"
            placeholder="First Name"
            value={formData.firstName}
            onChange={handleChange}
            className="border rounded-xl p-3"
            required
          />

          <input
            name="lastName"
            placeholder="Last Name"
            value={formData.lastName}
            onChange={handleChange}
            className="border rounded-xl p-3"
            required
          />

          <input
            name="email"
            type="email"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
            className="border rounded-xl p-3"
            required
          />

          <input
            name="phone"
            placeholder="Phone"
            value={formData.phone}
            onChange={handleChange}
            className="border rounded-xl p-3"
            required
          />

          <input
            name="department"
            placeholder="Department"
            value={formData.department}
            onChange={handleChange}
            className="border rounded-xl p-3"
            required
          />

          <input
            name="semester"
            type="number"
            min="1"
            max="8"
            placeholder="Semester"
            value={formData.semester}
            onChange={handleChange}
            className="border rounded-xl p-3"
            required
          />

          <div className="col-span-2 flex justify-end gap-3 mt-4">

            <button
              type="button"
              onClick={() => {
                resetForm();
                onClose();
              }}
              className="px-5 py-2 border rounded-xl"
            >
              Cancel
            </button>

            <button
              type="submit"
              className="px-5 py-2 bg-[#2B2B2B] text-white rounded-xl"
            >
              {isEditMode
                ? "Update"
                : "Register"}
            </button>

          </div>

        </form>

      </div>

    </div>
  );
}

export default StudentModal;