class AddFieldsToUser < ActiveRecord::Migration
  def change
    add_column :users, :facebook, :string
    add_column :users, :cellphone, :string
  end
end
